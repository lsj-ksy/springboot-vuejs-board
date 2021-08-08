package com.sjsy.springvue.domain.main;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {

    //메인의 글(content)
    @Query("select c from Content c where c.enabled = 1")
    Content mainContent();

/*
    //메인의 파일들(content_files)
    @Query("select c from ContentFile c where c.id = :id")
    List<ContentFile> mainFileListById(Long id);
*/

}
