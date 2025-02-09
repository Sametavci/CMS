package com.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.infrastructure.persistence.repositorys")
@EntityScan(basePackages = "com.infrastructure.persistence.entities") // 👈 Entity’leri burada tarıyoruz!

@SpringBootApplication(scanBasePackages = "com")
public class CmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmsApplication.class, args);
	}

}
