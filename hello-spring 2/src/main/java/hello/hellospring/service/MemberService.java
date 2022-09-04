package hello.hellospring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class MemberService {

	private final MemberRepository memberRepository;
	
	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	 
	//회원가입

	public long join(Member member) {

		long start = System.currentTimeMillis();
		//같은 이름이 있는 중복회원은 X
		try{
			validateDuplicateMember(member); //중복 회원 검증 옵션+커맨드+m 메소드 추출
			memberRepository.save(member);

			return member.getId();
		}finally {
			long finish = System.currentTimeMillis();
			long timeMs = finish-start;
			System.out.println("join = "+timeMs + "ms");
		}

	}


	private void validateDuplicateMember(Member member) {
		memberRepository.findByName(member.getName())
			.ifPresent(m -> { 
			throw new IllegalStateException("이미 존재하는 회원입니다.");
			});
	}
	
	//	전체회원 조회
	public List<Member> findMembers() {
		long start = System.currentTimeMillis();
		try{
			return memberRepository.findAll();
		}finally {
			long finish = System.currentTimeMillis();
			long timeMs = finish - start;
			System.out.println("findMembers = " + timeMs +"ms");
		}

	}
	
	public Optional<Member> findOne(Long memberId){
		return memberRepository.findById(memberId);
	}
}
