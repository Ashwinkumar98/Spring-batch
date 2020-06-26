package com.customer.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.customer.batch.modal.Customer;

/*
 * Spring batch configuration
 * */

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Value("${input}")
	private Resource resource;
	
	@Value("${file.index.name}")
	private String[] index;
	
	@Autowired
	private ItemWriter<Customer> writer;
	
	@Autowired
	private ItemProcessor<Customer, Customer> processor;
	
	@Bean
	public Job job() {
		return jobBuilderFactory.get("Customer-Job")
				                .incrementer(new RunIdIncrementer())
				                .start(step())
				                .build();
	}
	
	@Bean
	public Step step() {
		return stepBuilderFactory.get("customer-step")
								 .<Customer,Customer>chunk(1)
								 .reader(itemReader())
								 .processor(processor)
								 .writer(writer)
								 .build();
	}
	
	@Bean
	public FlatFileItemReader<Customer> itemReader(){
		FlatFileItemReader<Customer> flatItemReader = new FlatFileItemReader<Customer>();
		flatItemReader.setResource(resource);
		flatItemReader.setName("customer-reader");
		flatItemReader.setLinesToSkip(1);
		flatItemReader.setLineMapper(lineMapper());
		return flatItemReader;
	}
	
	public LineMapper<Customer> lineMapper(){
		
		DefaultLineMapper<Customer> lineMapper = new DefaultLineMapper<Customer>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames(index);
		
		
		BeanWrapperFieldSetMapper<Customer> fieldSetMapper = new BeanWrapperFieldSetMapper<Customer>();
		fieldSetMapper.setTargetType(Customer.class);
		
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		
		return lineMapper;
	}
}
