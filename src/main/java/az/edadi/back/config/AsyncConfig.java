package az.edadi.back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean("sendMailExecutor")
    Executor mailExecutor() {
        return Executors.newFixedThreadPool(10);
    }

    @Bean("deleteFileExecutor")
    Executor deleteFile() {
        return Executors.newFixedThreadPool(30);
    }

}
