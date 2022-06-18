package com.example.practicebatch.batch.job.hello;

import org.junit.runner.RunWith;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBatchTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {HelloJob.class})
class HelloJobTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

}
