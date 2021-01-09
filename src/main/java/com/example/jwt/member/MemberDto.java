package com.example.jwt.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDto {

    private String email;
    private String password;

    public MemberDto(Member member) {
        this.email = member.getEmail();
        this.password = member.getPassword();
    }
}
