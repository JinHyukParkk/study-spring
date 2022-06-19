package com.example.practicebatch.batch.step.hello;

import com.example.practicebatch.batch.TestConfiguration;
import org.junit.runner.RunWith;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBatchTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        TestConfiguration.class,
        HelloStep.class})
class HelloStepTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

}
