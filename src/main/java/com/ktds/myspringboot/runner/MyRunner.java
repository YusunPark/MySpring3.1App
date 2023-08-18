package com.ktds.myspringboot.runner;

import com.ktds.myspringboot.dto.Customer;
import com.ktds.myspringboot.property.MybootProperties;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements ApplicationRunner {

    @Value("${myboot.name}")
    private String name;
    @Value("${myboot.age}")
    private int age;

    @Autowired
    MybootProperties properties;

//    @Autowired // Autowired는 클래스의 type으로 찾기에, 타입이 같은게 있으면 qualifier 사용해야한다.
//    Customer customer;
    // Autowired 방법 말고 등록된 bean을 가져오는 법
    @Resource(name="myCustomer") // Bean의 이름으로 찾는다. Autowired + Qualifier 합친거
    Customer customer;

    @Autowired
    Environment environment;

    private Logger logger = LoggerFactory.getLogger(MyRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Port Number = {}", environment.getProperty("local.server.port"));
        logger.info("Logger 구현 클래스 이름 = {} (중괄호 안으로 값이 들어감)", logger.getClass().getName());

        logger.info("VM Argument = " + args.containsOption("foo")); // false
        logger.info("Program Argument = " + args.containsOption("bar")); // true
        logger.info("******환경변수 문자열 설정*********");
        logger.info("myboot.name : " + name);
        logger.info("myboot.age : " + age);

        logger.debug("******환경변수 property 설정*********");
        logger.debug("properties.getName() : " + properties.getName());
        logger.debug("properties.getAge() : " + properties.getAge());

        logger.debug("******Profile : myCustomer 빈에 설정된 내용 *********");
        logger.debug("customer bean : " + customer);

        logger.debug("----------------------");
        logger.debug("logger properties.getName() : " + properties.getName());
    }
}
