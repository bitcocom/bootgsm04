package com.example.bootgsm04.entity;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
// 게시물(번호,제목,내용,작성일,회원id(FK))
@Entity
public class Post { // post
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String title;
   private String content;
   private LocalDateTime regdate=LocalDateTime.now(); // ?
   // member_id(FK)
   @ManyToOne
   @JoinColumn(name = "member_id") // FK(외래키)
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

   public LocalDateTime getRegdate() {
      return regdate;
   }

   public void setRegdate(LocalDateTime regdate) {
      this.regdate = regdate;
   }

   public Member getMember() {
      return member;
   }

   public void setMember(Member member) {
      this.member = member;
   }
}
