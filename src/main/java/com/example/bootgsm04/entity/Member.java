package com.example.bootgsm04.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity // ORM -> table
public class Member {  // 권한(ROLE)
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 일련번호(자동증가) 1,2,3,~~
    @Column(length = 50, nullable = false, unique = true)
    //@ColumnDefault("'user'")
    private String username;// -> memId
    private String password; // -> memPwd
    private String memName;
    private int memAge;
    @Column(length = 50, nullable = false, unique = true)
    private String memEmail;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "member", cascade = CascadeType.ALL)
    private List<Post> posts;

    // 권한저장필드 : 1개권한->?[여러개 권한?(Member<-M -T- N->Role)]
    //SELECT * FROM gsm02.member_roles;
    // member_roles(member_id, role_id)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "member_roles",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles; // "USER,MANAGER,ADMIN"

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName;
    }

    public int getMemAge() {
        return memAge;
    }

    public void setMemAge(int memAge) {
        this.memAge = memAge;
    }

    public String getMemEmail() {
        return memEmail;
    }

    public void setMemEmail(String memEmail) {
        this.memEmail = memEmail;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", memName='" + memName + '\'' +
                ", memAge=" + memAge +
                ", memEmail='" + memEmail + '\'' +
                ", posts=" + posts +
                '}';
    }
}
