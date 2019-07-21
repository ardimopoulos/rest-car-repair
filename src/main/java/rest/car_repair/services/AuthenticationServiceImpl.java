package rest.car_repair.services;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import rest.car_repair.domain.Member;
import rest.car_repair.exceptions.member.MemberNotFoundException;
import rest.car_repair.repositories.MemberRepository;
import rest.car_repair.security.JwtTokenProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{

    private JwtTokenProvider tokenProvider;
    private AuthenticationManager authenticationManager;
    private MemberRepository memberRepository;

    @Override
    public String getToken(String username, String password) throws MemberNotFoundException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        Optional<Member> persistentMember = memberRepository.findByEmail(username);

        if (!persistentMember.isPresent()) {
            throw new MemberNotFoundException("Member not found");
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(persistentMember.get().getUserType()));

        return tokenProvider.createToken(username, roles);
    }
}
