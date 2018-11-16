package org.springframework.cloud.task.app.parser.batch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import mx.infotec.dads.costos.Costo;

/**
 * 
 * @author Roberto Villarejo Martínez
 *
 */
@EnableTask
@EnableBatchProcessing
@Configuration
@EnableConfigurationProperties({ ParserBatchTaskProperties.class })
@EnableMongoRepositories(basePackages = { "mx.infotec.dads.kukulkan.costos" })
public class ParserBatchTaskConfiguration {

    private static final Log logger = LogFactory.getLog(ParserBatchTaskProperties.class);

    @Autowired
    public JobBuilderFactory jobBuilder;

    @Autowired
    public StepBuilderFactory stepBuilder;

    @Autowired
    private ParserBatchTaskProperties config;

    @Bean
    public ItemReader<Costo> reader() {
        return new CostoReader();
    }

    @Bean
    public ItemWriter<Costo> writer() {
        return new CostoWriter();
    }

    @Bean
    public Step step(ItemReader<Costo> reader, ItemWriter<Costo> writer) {
        return stepBuilder.get("parser").<Costo, Costo>chunk(config.getChunkSize()).reader(reader).writer(writer)
                .build();
    }

    @Bean
    public Job job() {
        return jobBuilder.get("excelParser").start(step(reader(), writer())).incrementer(new RunIdIncrementer())
                .build();
    }

}
