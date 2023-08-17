package com.ktds.myspringboot;

import com.ktds.myspringboot.dto.Customer;
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
        // forEach(Consumer)
        stringList.forEach(val -> System.out.println("val = " + val));
        // Message Reference
        stringList.forEach(System.out::println);

    }

    @Test
    void consumer_test() {
        //Immutable List (dto.Customer이용)
        List<Customer> customerList =
                List.of(new Customer("boot", 10),
                        new Customer("msa", 20),
                        new Customer("ktds", 50),
                        new Customer("wifi", 70));

        //1. Anonymous Inner Class
        customerList.forEach(new Consumer<Customer>() {
            @Override
            public void accept(Customer customer) {
                System.out.println(customer);
            }
        });
        //2. Lambda Expression
        customerList.forEach(cust -> System.out.println(cust));
        //3. Method Reference
        customerList.forEach(System.out::println);

        //Customer의 age 합계를 계산하기 (age >= 50)
        int sumOfAge =
                customerList.stream() //Stream<Customer>
                        .filter(customer -> customer.getAge() >= 50) //Stream<Customer> ->Predicate(return boolean)
                        //.mapToInt(customer -> customer.getAge()) // IntStream<Integer> -> Function(입출력 있고 + 타입이 다른경우)
                        .mapToInt(Customer::getAge)//IntStream -> 위와 동일한 코드임
                        .sum();
        System.out.println("나이 합계 " + sumOfAge);

        int asInt = customerList.stream()
                .mapToInt(Customer::getAge)
                .max()
                .getAsInt();
        System.out.println("asInt = " + asInt);
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
