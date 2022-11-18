package pro.fir.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pro.fir.domain.members.Member;
import pro.fir.domain.members.MemberRepository;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public Member saveMember(Member member){
        validateDuplicateMember(member);

        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember != null){
            try{
                throw new IllegalStateException("이미 가입된 회원입니다.");
            }catch (IllegalStateException e){
                e.getMessage();
            }
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);

        if(member == null){
            throw new UsernameNotFoundException(email);
        }

        return User.builder()
                .username(member.getEmail())
                .password(member.getEmail())
                .roles(member.getRole().toString())
                .build();
    }
}
