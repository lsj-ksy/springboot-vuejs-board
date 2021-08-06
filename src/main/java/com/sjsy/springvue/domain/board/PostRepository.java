package com.sjsy.springvue.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    //내가 쓴 게시글 갯수
    @Query("select count(p) from Post p where p.user.id = :id")
    int countPostsByUserid(Long id);

    //내가 쓴 게시글 리스트
    @Query("select p from Post p where p.user.id = :id")
    List<Post> findPostsByUserid(Long id);

}
