package com.example.jwt.member;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class MemberContext extends User {

    private Member member;

    public MemberContext(Member member) {
        super(member.getEmail(), member.getPassword(), AuthorityUtils.createAuthorityList(Role.USER.getValue()));
    }
}
