/*
 *  
 * The MIT License (MIT)
 * Copyright (c) 2018 Roberto Villarejo Martínez
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.springframework.cloud.task.app.parser.batch;

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

import mx.infotec.dads.costos.domain.DataFrameItem;

/**
 * 
 * @author Roberto Villarejo Martínez
 *
 */
@EnableTask
@EnableBatchProcessing
@Configuration
@EnableConfigurationProperties({ ParserBatchTaskProperties.class })
@EnableMongoRepositories(basePackages = { "mx.infotec.dads.costos" })
public class ParserBatchTaskConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilder;

    @Autowired
    public StepBuilderFactory stepBuilder;

    @Autowired
    private ParserBatchTaskProperties config;

    @Bean
    public ItemReader<DataFrameItem> reader() {
        return new DfItemReader();
    }

    @Bean
    public ItemWriter<DataFrameItem> writer() {
        return new DfItemWriter();
    }

    @Bean
    public Step step(ItemReader<DataFrameItem> reader, ItemWriter<DataFrameItem> writer) {
        return stepBuilder.get("parser").<DataFrameItem, DataFrameItem>chunk(config.getChunkSize()).reader(reader)
                .writer(writer).build();
    }

    @Bean
    public Job job() {
        return jobBuilder.get("excelParser").start(step(reader(), writer())).incrementer(new RunIdIncrementer())
                .build();
    }

}
