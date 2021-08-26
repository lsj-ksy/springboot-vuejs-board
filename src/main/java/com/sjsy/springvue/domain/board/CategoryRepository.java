package com.sjsy.springvue.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "select * from board_category where enabled = 1",nativeQuery = true)
    List<Category> findAllByEnabled();
}
