package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    //ctrl + shift + t: 테스트 만들기
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원X
        //ctrl + alt + v: 변수 자동완성
        //alt + insert: getter setter 자동완성
        //ctrl + shift + Enter: 줄 자동완성
        //Optional<Member> result = memberRepository.findByName(member.getName());
        //Optional로 감싸서 ifPresent 같은 걸 쓸 수 있다
        //ctrl + alt + shift: refractor
        //ctrl + alt + m : 메소드로 뽑기
        long start = System.currentTimeMillis();
        try {
            validateDuplicateMember(member);// 중복 회원 검증
            memberRepository.save(member);
            return member.getId();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs =finish - start;
            System.out.println("join = " + timeMs + "ms");
        }
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
