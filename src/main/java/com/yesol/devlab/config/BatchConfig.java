package com.yesol.devlab.config;

import com.yesol.devlab.excelReader.ExcelReader;
import com.yesol.devlab.repository.UsersRepository;
import com.yesol.devlab.vo.Users;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.IOException;
import java.util.List;

@Configuration
public class BatchConfig {
    //private final JobBuilderFactory jobBuilderFactory;
    //private final StepBuilderFactory stepBuilderFactory;
    private final UsersRepository usersRepository; // JPA Repository
    private final PlatformTransactionManager transactionManager;
    private final ExcelReader excelReader;

    public BatchConfig(//JobBuilderFactory jobBuilderFactory,
                       //StepBuilderFactory stepBuilderFactory,
                       UsersRepository usersRepository,
                       PlatformTransactionManager transactionManager, ExcelReader excelReader) {
        //this.jobBuilderFactory = jobBuilderFactory;
        //this.stepBuilderFactory = stepBuilderFactory;
        this.usersRepository = usersRepository;
        this.transactionManager = transactionManager;
        this.excelReader = excelReader;
    }

    @Bean
    public Job importUserJob(JobRepository jobRepository) throws IOException {
        return new JobBuilder("importUserJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(importUserStep())
                .end()
                .build();
    }
    @Bean
    public Step importUserStep(JobRepository jobRepository, Tasklet myTasklet, PlatformTransactionManager transactionManager) throws IOException {
        return new StepBuilder("myStep", jobRepository)
                .<Users, Users>chunk(10) // 청크 사이즈를 10으로 설정
                .reader(excelReader()) // 엑셀 리더
                .processor(excelProcessor()) // 데이터 프로세서
                .writer(excelWriter()) // 데이터 라이터
                .transactionManager(transactionManager) // 트랜잭션 매니저
                .build();
    }

    @Bean
    public ListItemReader<Users> excelReader() throws IOException {
        List<Users> usersList = excelReader.readExcel(); // 엑셀에서 데이터 읽기
        return new ListItemReader<>(usersList);
    }

    @Bean
    public ItemProcessor<Users, Users> excelProcessor() {
        return user -> {
            // 필요시 데이터 변환 로직 추가
            return user;
        };
    }

    @Bean
    public ItemWriter<Users> excelWriter() {
        return users -> {
            usersRepository.saveAll(users); // DB에 저장
        };
    }

}
