package com.sjsy.springvue.service.post;

import com.sjsy.springvue.domain.board.*;
import com.sjsy.springvue.domain.user.User;
import com.sjsy.springvue.domain.user.UserRepository;
import com.sjsy.springvue.util.FileHandler;
import com.sjsy.springvue.web.dto.PostSaveReqDto;
import com.sjsy.springvue.web.dto.PostsListResDto;
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

    @Transactional(readOnly = true)
    public int count(Long id) {
        return postRepository.countPostsByUserid(id);
    }

    @Transactional(readOnly = true)
    public List<PostsListResDto> find(Long id) {
        return postRepository.findPostsByUserid(id).stream()
                .map(PostsListResDto::new)
                .collect(Collectors.toList());
    }

    // (게시물 등록)
    private User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not Found User id = " + id));
    }

    // (게시물 등록)
    private Board getBoardById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not Found Board id = " + id));
    }

    //게시물 등록
    @Transactional
    public Long postSave(Optional<List<MultipartFile>> fileList, PostSaveReqDto postSaveReqDto) throws Exception {
        //Dto의 toEntity() 메서드를 통해 Post 타입으로 만들어주고
        Post savePost = postSaveReqDto.toEntity(getUserById(Long.parseLong(postSaveReqDto.getUserId())),
                                                getBoardById(Long.parseLong(postSaveReqDto.getBoardId())));

        if(fileList.isPresent()) { //fileList가 Null이 아니라면 (첨부된 파일이 있다면)

            //util에 직접 만들어준 fileHandler 안에 List<PostFile>로 바꿔주는 메서드, 그리고 upload 폴더 안에 만들어질 폴더 이름 넣어줘야 함
            List<PostFile> postFileList = fileHandler.parsePostFileList(fileList.get(), "postfile");

            //forEach 돌면서(파일이 1개이든 다중파일이든) postFile 저장됨
            postFileList.forEach(postFile -> savePost.addPostFile(postFileRepository.save(postFile)));
        }
        return postRepository.save(savePost).getId(); //post 저장 후 getId 하여 id값 리턴
    }
}
