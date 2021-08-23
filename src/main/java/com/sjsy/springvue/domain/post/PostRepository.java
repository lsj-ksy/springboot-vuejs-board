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

    //게시판 글보기(BoardType 별)
    //@Query("select p from Post p where p.enabled = 1 AND p.board.boardName = :boardType")
    //List<Post> findAllByBoardInfo(String boardType);
    @Query(value = "select * from board_post left join board_info bi on bi.id = board_post.board_id where board_name = \"test_board\" and board_post.enabled = 1", nativeQuery = true)
    List<Post> findAllByBoardInfo(String boardType);

    //단어 검색으로 글 리스트 보기
    @Query("SELECT p FROM Post p WHERE p.subject LIKE CONCAT('%',:search,'%') and p.enabled = 1")
    List<Post> findPostsBySearch(String search);

    //글쓰기 Ref값 구하기
    @Query(value = "select max(ref) from board_post", nativeQuery = true)
    int findMaxRef();


}
