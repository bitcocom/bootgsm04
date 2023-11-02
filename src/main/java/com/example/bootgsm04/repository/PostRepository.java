package com.example.bootgsm04.repository;

import com.example.bootgsm04.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
/*public class EntityManagerFactory implements PostRepository{

}*/

