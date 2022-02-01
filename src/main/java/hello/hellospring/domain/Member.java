package hello.hellospring.domain;

import  javax.persistance.Entity;

@Entity //JPA가 관리하는 entity다
public class Member {
    @Id //PK와
    @GeneratedValue(strategy=GenerationType.IDENTITY)   //DB가 ID를 알아서 자동생성
    private Long id;    //시스템이 저장하는 id

    //@Column(name="username") 컬럼명이 username
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
