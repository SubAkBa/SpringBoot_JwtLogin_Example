package com.example.jwt.member;

import com.example.jwt.advice.exception.AuthenticationEntryPointException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/error")
public class ExceptionConroller {

    @GetMapping("/entrypoint")
    public void entryPointException() {
        throw new AuthenticationEntryPointException("해당 리소스에 접근하기 위한 권한이 없습니다.");
    }

    @GetMapping("/accessdenied")
    public void accessDeniedException() {
        throw new AuthenticationEntryPointException("보유한 권한으로 접근할 수 없는 리소스입니다.");
    }
}
