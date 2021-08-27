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
    @Query(value = "select * from board_post where enabled = 1 order by ref desc, depth asc limit :page , :perPage" , nativeQuery = true)
    List<Post> findAllByBoardId(int page,int perPage);

    //게시판 글보기(board_info 의 id 별, 글순서 및 답글순서 정렬)
    @Query(value = "select * from board_post left join board_info bi on bi.id = board_post.board_id " +
            "where bi.id = :board_id and board_post.enabled = 1 order by ref desc, depth asc limit :page, :perPage", nativeQuery = true)
    List<Post> findAllByBoardId(Long board_id, int page, int perPage);

    //단어 검색으로 글 리스트 보기
    @Query("SELECT p FROM Post p WHERE p.subject LIKE CONCAT('%',:search,'%') and p.enabled = 1")
    List<Post> findPostsBySearch(String search);

    //글쓰기 Ref값 구하기
    @Query(value = "select max(ref) from board_post", nativeQuery = true)
    int findMaxRef();

    //전체게시판 글 개수
    @Query(value = "select count(*) from board_post where enabled = 1",nativeQuery = true)
    int countTotalPosts();

    //게시판 종류별 글 개수
    @Query(value = "select count(*) from board_post where enabled = 1 and board_id = :boardId",nativeQuery = true)
    int countTotalPosts(Long boardId);

    //게시판이 속한 카테고리 이름
    @Query(value = "select category_name from board_category where id = (select category_id from board_info where id = :boardId)", nativeQuery = true)
    String findCategoryByBoardId(Long boardId);

    //게시판 이름
    @Query(value = "select board_name from board_info where id = :boardId", nativeQuery = true)
    String findBoardId(Long boardId);




}
