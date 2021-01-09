package com.example.jwt.member;

import com.example.jwt.config.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MemberDto memberDto) {
        Member findMember = memberRepository.findByEmail(memberDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(memberDto.getEmail() + " not Exist"));

        if(!passwordEncoder.matches(memberDto.getPassword(), findMember.getPassword()))
            throw new BadCredentialsException("InValid " + memberDto.getPassword());

        return new ResponseEntity<>(jwtTokenProvider.generateToken(memberDto.getEmail()), HttpStatus.OK);
    }

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(memberRepository.save(member))
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/list")
    public List<MemberDto> list() {
        return memberRepository.findAll()
                .stream()
                .map(MemberDto::new)
                .collect(Collectors.toList());
    }
}
