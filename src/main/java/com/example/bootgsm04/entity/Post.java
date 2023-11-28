package com.example.bootgsm04.entity;
import javax.persistence.*;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import java.util.Date;
// 게시물(번호,제목,내용,작성일,회원id(FK))
@Entity
public class Post { // post
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String title;
   private String content;

   @CreationTimestamp
   private Timestamp regdate;
   // member_id(FK)
   @ManyToOne
   @JoinColumn(name = "member_id") // FK(외래키)
   @JsonIgnore
   private Member member; //id
  /* alter table post
   add constraint fk_post_member_id
   foreign key (member_id)
   references member (id)*/
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

    public Timestamp getRegdate() {
        return regdate;
    }

    public void setRegdate(Timestamp regdate) {
        this.regdate = regdate;
    }

    public Member getMember() {
      return member;
   }

   public void setMember(Member member) {
      this.member = member;
   }
}
