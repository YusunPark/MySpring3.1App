package com.ktds.myspringboot.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component // 스프링 빈 이기때문에 component를 추가해준다.
@ConfigurationProperties("myboot") // 타입-세이프 프로퍼티 클래스를 작성을 위해, myboot인 property를 찾아서 주입해줌
@Getter // 롬복의 게터 세터를 사용
@Setter
public class MybootProperties {
    private String name;
    private int age;
    private String fullName;


}
