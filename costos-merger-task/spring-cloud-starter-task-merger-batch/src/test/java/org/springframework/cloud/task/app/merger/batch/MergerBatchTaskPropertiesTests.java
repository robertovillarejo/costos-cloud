package org.springframework.cloud.task.app.merger.batch;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindException;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.util.EnvironmentTestUtils;
import org.springframework.cloud.task.app.merger.batch.MergerBatchTaskProperties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

public class MergerBatchTaskPropertiesTests {

    @Test(expected = ConfigurationPropertiesBindException.class)
    public void testEmptyFormat() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        EnvironmentTestUtils.addEnvironment(context, "merger.chunkSize:");
        context.register(Conf.class);
        context.refresh();
        MergerBatchTaskProperties properties = context.getBean(MergerBatchTaskProperties.class);
        properties.getChunkSize();
    }

    @Test
    public void testFormatDefault() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Conf.class);
        context.refresh();
        MergerBatchTaskProperties properties = context.getBean(MergerBatchTaskProperties.class);
        assertEquals("result does not match default format.", 10, properties.getChunkSize());
    }

    @Test
    public void testFormatSet() {
        final int chunkSize = 10;
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Conf.class);
        context.refresh();
        MergerBatchTaskProperties properties = context.getBean(MergerBatchTaskProperties.class);
        properties.setChunkSize(10);
        ;
        assertEquals("result does not match established format.", chunkSize, properties.getChunkSize());
    }

    @Configuration
    @EnableConfigurationProperties(MergerBatchTaskProperties.class)
    static class Conf {
    }
}
