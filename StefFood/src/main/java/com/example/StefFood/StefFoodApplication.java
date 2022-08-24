package com.example.StefFood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@SpringBootApplication
@EnableCaching
public class StefFoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(StefFoodApplication.class, args);
	}

	}

//	@Bean
//	public DataSource dataSource(){
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//		dataSource.setUrl("jdbc:mysql://localhost:3306/");
//		dataSource.setUsername("root");
//		dataSource.setPassword("Camila@01");
//		return dataSource;
//}

