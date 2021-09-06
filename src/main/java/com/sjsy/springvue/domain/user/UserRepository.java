package com.sjsy.springvue.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //userid 로 User 찾기
    @Query("select u from User u where u.id = :id")
    User findUserByUserid(Long id);

    @Query("select u from User u where u.nickname = :nickname")
    int findNickname(String nickname);

    //로그인에 사용될 소셜키로 가입여부 체크(옵셔널) >> 아래 위 둘 중 하나 지울 예정입니다 (__)
    //Optional<User> findBySocialKeyOptional(String socialKey);

    //로그인에 사용될 소셜키로 가입 여부 체크
    User findBySocialKey(String socialKey);

}
