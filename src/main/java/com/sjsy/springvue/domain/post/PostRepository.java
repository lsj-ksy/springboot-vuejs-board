package com.sjsy.springvue.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    //내가 쓴 게시글 갯수
    @Query("select count(p) from Post p where p.user.id = :id")
    int countPostsByUserid(Long id);

    //내가 쓴 게시글 리스트
    @Query("select p from Post p where p.user.id = :id")
    List<Post> findPostsByUserid(Long id);

    //전체글보기(메인페이지)
    @Query("select p from Post p where p.enabled = 1")
    List<Post> findAllByEnabled();

    //단어 검색으로 글 리스트 보기
    @Query("SELECT p FROM Post p WHERE p.subject LIKE CONCAT('%',:search,'%') and p.enabled = 1")
    List<Post> findPostsBySearch(String search);

}
