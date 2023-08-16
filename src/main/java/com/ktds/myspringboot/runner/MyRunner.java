package com.ktds.myspringboot.runner;

import com.ktds.myspringboot.dto.Customer;
import com.ktds.myspringboot.property.MybootProperties;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
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

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("VM Argument = " + args.containsOption("foo")); // false
        System.out.println("Program Argument = " + args.containsOption("bar")); // true
        System.out.println("******환경변수 문자열 설정*********");
        System.out.println("myboot.name : " + name);
        System.out.println("myboot.age : " + age);

        System.out.println("******환경변수 property 설정*********");
        System.out.println("properties.getName() : " + properties.getName());
        System.out.println("properties.getAge() : " + properties.getAge());

        System.out.println("******Profile : myCustomer 빈에 설정된 내용 *********");
        System.out.println("customer bean : " + customer);
    }
}
