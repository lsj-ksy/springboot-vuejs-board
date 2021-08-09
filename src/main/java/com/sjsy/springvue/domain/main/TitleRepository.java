package com.sjsy.springvue.domain.main;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TitleRepository extends JpaRepository<Title, Long> {

    //해당 DB의 컬럼은 한개만 유지되도록 설계됐습니다
    @Query("select t from Title t")
    Title findTitleFile();

}
