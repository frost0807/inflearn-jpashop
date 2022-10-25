package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    //엔티티나 입베디드 타입(@Embeaable)은 자바 기본 생성자를 public 또는 protected로 설정해야 한다.
    //이런 제약을 두는 이유는 JPA 구현 라이브러리가 객체를 생성할 때 리플렉션 같은 기술을 사용할 수 있도록 지원해야 하기 때문
    protected Address() {
    }

    //값 타입(Embedded)은 변경 불가능하게 설계해야 하므로 생성자를 통한 초기화 구현
    public Address(String city, String street, String zipcode) { 
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
