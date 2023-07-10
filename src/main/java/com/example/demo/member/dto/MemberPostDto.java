package com.example.demo.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberPostDto {
    @NotBlank(message = "이름은 공백이 아니어야 합니다.")
    private String userName;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@!%*#?&])[A-Za-z\\d$@!%*#?&]{8,}$",
            message = "최소 8자, 최소 각 하나의 문자, 숫자 및 특수문자(@$!%*#?&)가 필요합니다.")
    private String password;

    @NotBlank
    @Email
    private String email;
}
