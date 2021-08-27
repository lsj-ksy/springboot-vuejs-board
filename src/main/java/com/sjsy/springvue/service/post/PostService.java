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
import com.sjsy.springvue.web.dto.response.PostDetailResDto;
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

    //게시물 수정
    @Transactional
    public Long postUpdate(Long id, Optional<List<MultipartFile>> fileList, PostUpdateReqDto postUpdateReqDto) throws Exception {
        Post updatePost = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not Found Post id = " + id));

        if(!String.valueOf(updatePost.getUser().getId()).equals(postUpdateReqDto.getUserId())) {
            throw new IllegalArgumentException("No Match User id = [Request] " + postUpdateReqDto.getUserId() + " & [Response] " + updatePost.getUser().getId().toString());
        }

        if(fileList.isPresent()) {
            List<PostFile> postFileList = fileHandler.parsePostFileList(fileList.get(), "postfile");
            postFileList.forEach(postFile -> updatePost.addPostFile(postFileRepository.save(postFile)));
        }

        updatePost.update(postUpdateReqDto.getSubject(), postUpdateReqDto.getContent());
        return id;
    }


    //main 전체글보기 dto response service
    @Transactional(readOnly = true)
    public List<PostsListResDto> findAllByEnabled(Long board_id, int page, int perPage) {

        page = (page - 1) * 10 ;

        return postRepository.findAllByEnabled(page, perPage).stream()
                .map(PostsListResDto::new)
                .collect(Collectors.toList());
    }

    //main 전체글보기 dto response service
    @Transactional(readOnly = true)
    public List<PostsListResDto> findAllByBoardId(Long boardId, int page, int perPage) {

        page = (page - 1) * 10 ;

        if(boardId == 0) { //전체글보기
            return postRepository.findAllByEnabled(page, perPage).stream()
                    .map(PostsListResDto::new)
                    .collect(Collectors.toList());
        } else { //게시판 종류별 글보기
            return postRepository.findAllByBoardId(boardId, page, perPage).stream()
                    .map(PostsListResDto::new)
                    .collect(Collectors.toList());
        }
    }
}
