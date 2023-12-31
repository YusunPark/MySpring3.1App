package com.ktds.myspringboot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserReqDto {
    @NotEmpty(message = "Name은 필수 입력 항목입니다.")
    private String name;
    @NotBlank(message = "Email은 문자열 필수 입력 항목입니다.")
    private String email;

}
