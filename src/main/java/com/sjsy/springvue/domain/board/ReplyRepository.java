package com.sjsy.springvue.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    //내가 쓴 댓글 갯수
    @Query("select count(r) from Reply r where r.user.id = :id")
    int countReplysByUserid(Long id);
}
