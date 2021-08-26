package com.sjsy.springvue.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query(value = "select * from board_info where enabled = 1",nativeQuery = true)
    List<Board> findAllByEnabled();

    @Query(value = "select * from board_info where category_id = :categoryId and enabled = 1",nativeQuery = true)
    List<Board> findByCategoryId(Long categoryId);
}
