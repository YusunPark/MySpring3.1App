package com.ktds.myspringboot.runner;

import com.ktds.myspringboot.property.MybootProperties;
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
    }
}
