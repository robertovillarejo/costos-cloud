package org.springframework.cloud.task.app.parser.batch;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindException;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.util.EnvironmentTestUtils;
import org.springframework.cloud.task.app.parser.batch.ParserBatchTaskProperties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

public class ParserBatchTaskPropertiesTests {

    @Test(expected = ConfigurationPropertiesBindException.class)
    public void testEmptyFormat() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        EnvironmentTestUtils.addEnvironment(context, "parser.chunkSize:");
        context.register(Conf.class);
        context.refresh();
        ParserBatchTaskProperties properties = context.getBean(ParserBatchTaskProperties.class);
        properties.getChunkSize();
    }

    @Test
    public void testFormatDefault() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Conf.class);
        context.refresh();
        ParserBatchTaskProperties properties = context.getBean(ParserBatchTaskProperties.class);
        assertEquals("result does not match default format.", 10, properties.getChunkSize());
    }

    @Test
    public void testFormatSet() {
        final int chunkSize = 10;
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Conf.class);
        context.refresh();
        ParserBatchTaskProperties properties = context.getBean(ParserBatchTaskProperties.class);
        properties.setChunkSize(10);
        ;
        assertEquals("result does not match established format.", chunkSize, properties.getChunkSize());
    }

    @Configuration
    @EnableConfigurationProperties(ParserBatchTaskProperties.class)
    static class Conf {
    }
}
