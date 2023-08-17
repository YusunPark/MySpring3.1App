package com.ktds.myspringboot;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.ModelMap;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

//		SpringApplication.run(Application.class, args);
//		SpringApplication application = new SpringApplication(Application.class);
//		application.setWebApplicationType(WebApplicationType.NONE);
//		application.run(args);

		SpringApplication application = new SpringApplication(Application.class);
		application.setWebApplicationType(WebApplicationType.SERVLET);
		application.run(args);

	}

	@Bean // 외부 라이브러리 등록 시
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
