package org.springframework.cloud.task.app.rules.batch;

import org.junit.Rule;
import org.junit.Test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.rule.OutputCapture;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class RulesBatchTaskTests {

//    @Rule
//    public OutputCapture outputCapture = new OutputCapture();
//
//    @Test
//    public void testTimeStampApp() throws Exception {
//        final String TEST_DATE_DOTS = ".......";
//        final String CREATE_TASK_MESSAGE = "Creating: TaskExecution{executionId=";
//        final String UPDATE_TASK_MESSAGE = "Updating: TaskExecution with executionId=1 with the following";
//        final String JOB1_MESSAGE = "Job1 was run with date ";
//        final String JOB2_MESSAGE = "Job2 was run with date ";
//
//        String[] args = { "--timestamp.format=yyyy" + TEST_DATE_DOTS };
//
//        SpringApplication.run(TestTimestampBatchTaskApplication.class, args);
//
//        String output = this.outputCapture.toString();
//
//        assertThat(output, containsString(TEST_DATE_DOTS));
//        assertThat(output, containsString(CREATE_TASK_MESSAGE));
//        assertThat(output, containsString(UPDATE_TASK_MESSAGE));
//
//        assertThat(output, containsString(JOB1_MESSAGE));
//        assertThat(output, containsString(JOB2_MESSAGE));
//
//    }
//
//    @SpringBootApplication
//    public static class TestTimestampBatchTaskApplication {
//        public static void main(String[] args) {
//            SpringApplication.run(TestTimestampBatchTaskApplication.class, args);
//        }
//    }

}
