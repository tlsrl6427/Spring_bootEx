package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//JpaRepository를 extends하면 Spring Bean에 자동등록
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    //select m from Member m where m.name = ?
    //findBy??? -> ???을 알아서 매핑해줌
    @Override
    Optional<Member> findByName(String name);
}
