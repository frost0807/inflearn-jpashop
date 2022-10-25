package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) //Spring이랑 엮어서 실행하는 어노테이션
@SpringBootTest //SpringBoot를 띄운 상태로 Test할 때 쓰는 어노테이션(Spring Container)
@Transactional //테스트하고 테스트 끝나먼 모두 롤백(Service나 Repository에 붙이면 롤백하지 않는다)
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    @Rollback(false)
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long savedId = memberService.join(member);

        //then
        //JPA는 DB에 commit을 하는순간 flush가 되면서 영속성 컨텍스트에서 쿼리가 나가는데
        //@Transactional이 붙어있으면 JPA입장에서는 insert를 날릴 이유조차도 없으므로 insert쿼리가 나가지 않는다.
        //그래서 강제로 flush를 한번 해주는 것
        //em.flush();
        assertEquals(member, memberRepository.findOne(savedId));

    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        memberService.join(member2); //예외가 발생해야 한다.

        //then
        fail("예외가 발생해야 한다.");
    }
}