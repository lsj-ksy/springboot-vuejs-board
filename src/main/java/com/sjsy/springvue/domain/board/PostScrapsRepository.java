package com.sjsy.springvue.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostScrapsRepository extends JpaRepository<Reply, Long> {

    //내가 쓴 댓글 갯수
    @Query("select count(p) from PostScrap p where p.user.id = :id")
    int countScrapsByUserid(Long id);
}
