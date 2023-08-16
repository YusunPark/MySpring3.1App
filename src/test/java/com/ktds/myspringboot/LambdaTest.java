package com.ktds.myspringboot;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Consumer;

public class LambdaTest {

    @Test
    void consumer() {
        List<String> stringList = List.of("Java", "Python", "Kotlin"); // Immutable List
        // Anonymous Inner Class -
        stringList.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("s = "+ s);
            }
        });

        // Lambda Expression
        stringList.forEach(val -> System.out.println("val = " + val));

    }

    @Test
    void runnable() {
        // Anonymous Inner Class -> 자신은 이름이 없으니까 부모의 이름을 쓴다.
        // 함수형 인터페이스는 자신의 추상메서드를 1개만 가질 수 있다. -> 이걸 람다로 쓸 수 있다.
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous Inner Class t1");
            }
        });
        t1.start();

        // LambdaExpression
        Thread t2 = new Thread(()-> System.out.println("Lambda Expression t2 "));
        t2.start();

    }
}
