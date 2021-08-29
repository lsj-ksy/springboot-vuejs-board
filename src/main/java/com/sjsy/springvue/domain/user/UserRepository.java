package com.sjsy.springvue.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //userid 로 User 찾기
    @Query("select u from User u where u.id = :id")
    User findUserByUserid(Long id);

    @Query("select u from User u where u.nickname = :nickname")
    int findNickname(String nickname);

    //로그인에 사용될 소셜키로 가입여부 체크(옵셔널)
    Optional<User> findBySocialKey(String socialKey);

}
