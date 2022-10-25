package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    /**
     기본생성자로 초기화 가능하지만 이 방식이 Best
     1. Null문제에서 안전하고
     2. Hibernate가 객체를 생성할 때 어떠한 방식으로 Collection을 감싸서 관리하기 때문에 객체 생성 후 Collection을 수정하지 말자
     * */
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
