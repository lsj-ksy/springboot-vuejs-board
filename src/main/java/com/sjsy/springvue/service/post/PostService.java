package com.sjsy.springvue.service.post;

import com.sjsy.springvue.domain.board.Board;
import com.sjsy.springvue.domain.board.BoardRepository;
import com.sjsy.springvue.domain.post.Post;
import com.sjsy.springvue.domain.post.PostFile;
import com.sjsy.springvue.domain.post.PostFileRepository;
import com.sjsy.springvue.domain.post.PostRepository;
import com.sjsy.springvue.domain.user.User;
import com.sjsy.springvue.domain.user.UserRepository;
import com.sjsy.springvue.util.FileHandler;
import com.sjsy.springvue.web.dto.request.PostSaveReqDto;
import com.sjsy.springvue.web.dto.request.PostUpdateReqDto;
import com.sjsy.springvue.web.dto.request.UserSidebarReqDto;
import com.sjsy.springvue.web.dto.response.BoardResDto;
import com.sjsy.springvue.web.dto.response.PostDetailResDto;
import com.sjsy.springvue.web.dto.response.PostWriteResDto;
import com.sjsy.springvue.web.dto.response.PostsListResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostFileRepository postFileRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final FileHandler fileHandler;

    //id로 PostList 찾기
    @Transactional(readOnly = true)
    public List<PostsListResDto> find(Long id) {
        return postRepository.findPostsByUserid(id).stream()
                .map(PostsListResDto::new)
                .collect(Collectors.toList());
    }

    //검색어로 게시물찾기
    @Transactional
    public List<PostsListResDto> findPostsBySearch(String search) {
        return postRepository.findPostsBySearch(search).stream()
                .map(PostsListResDto::new)
                .collect(Collectors.toList());
    }

    // (게시물 등록) user_id로 User 가져오기
    private User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not Found User id = " + id));
    }

    // (게시물 등록) board_id로 Board 가져오기
    private Board getBoardById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not Found Board id = " + id));
    }

    //게시물 등록(글쓰기, 답글쓰기)
    @Transactional
    public Long postSave(String type, //등록 타입(글쓰기(write) || 답글쓰기(reply))
                         Optional<List<MultipartFile>> fileList,
                         PostSaveReqDto postSaveReqDto)
            throws Exception {

        Post savePost = null;

        //type에 따라 Post 생성
        if(type.equals("write")){
            //새로 쓰여질 글의 ref 값
            int ref = postRepository.findMaxRef() + 1;

            savePost = postSaveReqDto.toEntity(getUserById(Long.parseLong(postSaveReqDto.getUserId())),
                    getBoardById(Long.parseLong(postSaveReqDto.getBoardId())), ref);

        } else { //type.equals("reply")
            savePost = postSaveReqDto.toEntity(getUserById(Long.parseLong(postSaveReqDto.getUserId())),
                    getBoardById(Long.parseLong(postSaveReqDto.getBoardId())));
        }

        if(fileList.isPresent()) { //fileList가 Null이 아니라면 (첨부된 파일이 있다면)

            //util에 직접 만들어준 fileHandler 안에 List<PostFile>로 바꿔주는 메서드, 그리고 upload 폴더 안에 만들어질 폴더 이름 넣어줘야 함
            List<PostFile> postFileList = fileHandler.parsePostFileList(fileList.get(), "postfile");

            //forEach 돌면서(파일이 1개이든 다중파일이든) postFile 저장됨
            Post finalSavePost = savePost;
            postFileList.forEach(postFile -> finalSavePost.addPostFile(postFileRepository.save(postFile)));
        }
        return postRepository.save(savePost).getId(); //post 저장 후 getId 하여 id값 리턴
    }

    //게시물 상세
    @Transactional(readOnly = true)
    public PostDetailResDto getPost(Long id) {
        Post entity = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not Found Post id = " + id));
        return new PostDetailResDto(entity);
    }


    //닉네임 변경 request service
    @Transactional(readOnly = true)
    public void userNicknameUpdate( UserSidebarReqDto userSidebarReqDto) {
        Optional<User> user = userRepository.findById(userSidebarReqDto.getId());

        user.ifPresent( selectUser -> { //조회된 column 의 닉네임 set 하여 save & flush
            selectUser.setNickname(userSidebarReqDto.getNickname());
            userRepository.save(selectUser);
            userRepository.flush(); //변동사항 적용
        });


    }

    //게시물 수정
    @Transactional
    public Long postUpdate(Long id, Optional<List<MultipartFile>> fileList, PostUpdateReqDto postUpdateReqDto) throws Exception {

        Post updatePost = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not Found Post id = " + id));

        if(!String.valueOf(updatePost.getUser().getId()).equals(postUpdateReqDto.getUserId())) {
            throw new IllegalArgumentException("No Match User id = [Request] " + postUpdateReqDto.getUserId() + " & [Response] " + updatePost.getUser().getId().toString());
        }

        //파일을 새로 업로드했으면 변동사항 적용
        if(fileList.isPresent()) {
            //원본파일 DB조회
            List<PostFile> originPostFiles = postFileRepository.findAllById(id);
            //원본파일 post_id 삭제
            originPostFiles.forEach(postFile -> postFile.deleteFile());
            //새로운파일 업로드 및 DB연동
            List<PostFile> postFileList = fileHandler.parsePostFileList(fileList.get(), "postfile");
            postFileList.forEach(postFile -> updatePost.addPostFile(postFileRepository.save(postFile)));
        }

        //변동사항 체크한후에 적용
        if(!updatePost.getSubject().equals(postUpdateReqDto.getSubject())) { //제목 변경
            updatePost.setSubject(postUpdateReqDto.getSubject());
        }
        if(!updatePost.getContent().equals(postUpdateReqDto.getContent())) { //내용 변경
            updatePost.setContent(postUpdateReqDto.getContent());
        }
        if(updatePost.getBoard().getId() != postUpdateReqDto.getBoardId()) { //카테고리 변경
            updatePost.setBoard(boardRepository.findById(postUpdateReqDto.getBoardId()).get());
        }

        //JPA update
        postRepository.save(updatePost); //jpa update 해줌
        postRepository.flush();

        return id;
    }

    //게시물 삭제
    @Transactional
    public int postDelete(Long postId, Long userId) {

        Post updatePost = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Not Found Post id = " + postId));

        if(updatePost.getUser().getId() != userId) {
            throw new IllegalArgumentException("No Match User id = [Request] " + userId + " & [Response] " + updatePost.getUser().getId().toString());
        }

        updatePost.setEnabled(0); //삭제

        postRepository.save(updatePost);
        postRepository.flush();

        return postRepository.findById(postId).get().getEnabled();
    }

    //게시판 게시글 리스트 dto response service
    @Transactional(readOnly = true)
    public BoardResDto findAllByBoardId(Long boardId, int page, int perPage) {

        page = (page - 1) * 10 ;

        if(boardId == 0) { //전체글보기

            //전체글 리스트
            List<PostsListResDto> postsListResDto = postRepository.findAllByBoardId(page, perPage).stream()
                    .map(PostsListResDto::new)
                    .collect(Collectors.toList());

            return BoardResDto.builder().categoryName("전체 게시판").boardName("전체 게시판").postList(postsListResDto).build();

        } else { //게시판 종류별 글보기

            //게시판 카테고리
            String categoryName = postRepository.findCategoryByBoardId(boardId);

            //게시판 이름
            String boardName = postRepository.findBoardId(boardId);

            //게시판 종류에 따른 게시글 리스트
            List<PostsListResDto> postsListResDto =postRepository.findAllByBoardId(boardId, page, perPage).stream()
                    .map(PostsListResDto::new)
                    .collect(Collectors.toList());

            return BoardResDto.builder().categoryName(categoryName).boardName(boardName).postList(postsListResDto).build();
        }
    }

    //전체게시판 글 개수 || 게시판 별 글 개수
    @Transactional(readOnly = true)
    public int findTotalCount(Long boardId) {
        if(boardId == 0) { //전체 게시판 글 개수
            return postRepository.countTotalPosts();
        } else {  //게시판 별 글 개수
            return postRepository.countTotalPosts(boardId);
        }
    }

}
