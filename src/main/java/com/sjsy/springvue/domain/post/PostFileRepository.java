package com.sjsy.springvue.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostFileRepository extends JpaRepository<PostFile, Long> {
    @Query(value = "select * from post_file where post_id = :id",nativeQuery = true)
    List<PostFile> findAllById(Long id);
}
