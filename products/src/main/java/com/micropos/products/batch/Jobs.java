package com.micropos.products.batch;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micropos.products.model.Product;
import com.micropos.products.repository.ProductDbPlain;

@EnableBatchProcessing
@Component
public class Jobs {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    ProductDbPlain productDbPlain;

    ObjectMapper mapper = new ObjectMapper();

    String filename = "meta_Magazine_Subscriptions_100";
    @Bean 
    public File defaultFile() throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResource("data/" + filename + ".json");
        return resource.getFile();
    }

    @Bean
    public ItemReader<JsonNode> jsonReader(File file) {
        return new FlatFileItemReaderBuilder<JsonNode>().lineMapper((line, n) -> {
            return mapper.readTree(line);
        }).name("jsonReader").resource(new FileSystemResource(file)).build();
    }

    @Bean
    public ItemProcessor<JsonNode, Product> jsonProcesser() {
        Random rand = new Random();
        return new ItemProcessor<JsonNode, Product>() {
            @Override
            public Product process(JsonNode item) throws Exception {
                Product result = new Product();
                if (item.hasNonNull("asin")) {
                    result.setId(item.get("asin").asText());
                }
                result.setId(UUID.randomUUID().toString());
                if (item.hasNonNull("price")) {
                    double price = item.get("price").asDouble();
                    if (price == 0.0) {
                        price = rand.nextDouble() * 10;
                    }
                    result.setPrice(price);
                }
                if (item.hasNonNull("title")) {
                    result.setName(item.get("title").asText());
                }
                if (item.hasNonNull("description")) {
                    JsonNode sub = item.get("description");
                    if (sub.isArray()) {
                        String description = "";
                        for (JsonNode node : sub) {
                            description += node.asText();
                        }
                        result.setDescription(description);
                    }
                }
                if (item.hasNonNull("imageURLHighRes")) {
                    JsonNode sub = item.get("imageURLHighRes");
                    if (sub.isArray()) {
                        if (sub.hasNonNull(0)) {
                            result.setImage(sub.get(0).asText());
                        }
                    }
                }
                return result;
            }
        };
    }

    @Bean
    public ItemWriter<Product> productWriter() {
        return new ItemWriter<Product>() {
            @Override
            public void write(List<? extends Product> items) throws Exception {
                productDbPlain.saveAll(items);
            }
        };
    }

    @Bean
    public Step processProducts(File file) {
        return stepBuilderFactory.get("processProducts")
                .<JsonNode, Product>chunk(100)
                .reader(jsonReader(file))
                .processor(jsonProcesser())
                .writer(productWriter())
                .allowStartIfComplete(true)  // allow restart
                .build();
    }

    @Bean
    public Job importProducts(File file) {
        return jobBuilderFactory.get("importProducts")
                .start(processProducts(file))
                .build();
    }
}
