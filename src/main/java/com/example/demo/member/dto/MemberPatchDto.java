package com.example.demo.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
//@Setter
public class MemberPatchDto {
    private long memberId;
    private String userName;
    private String password;
    private String email;
}
