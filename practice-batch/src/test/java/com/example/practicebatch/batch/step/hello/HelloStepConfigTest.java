package com.example.practicebatch.batch.step.hello;

import com.example.practicebatch.batch.TestConfiguration;
import com.example.practicebatch.batch.job.hello.HelloJobConfig;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBatchTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
    TestConfiguration.class,
    HelloJobConfig.class,
    HelloStepConfig.class})
class HelloStepConfigTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    public void printHelloStep_확인한다() {
        // given

        // when
        JobExecution jobExecution = jobLauncherTestUtils.launchStep("printHelloStep");

        // then
        assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.FAILED);
    }

    @Test
    public void throwErrorStep_확인한다() {
        // given

        // when
        JobExecution jobExecution = jobLauncherTestUtils.launchStep("throwErrorStep");

        // then
        assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
    }

}
