package com.study.jimcarry;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication //Spring Boot의 편의 주석으로, Spring Boot 애플리케이션을 구성하기 위해 여러 다른 주석을 결합
@EnableCaching
public class JimCarryApplication {

	public static void main(String[] args) {
		SpringApplication.run(JimCarryApplication.class, args);
	}

}
