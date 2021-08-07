package com.sjsy.springvue.service.post;

import com.sjsy.springvue.domain.board.PostRepository;
import com.sjsy.springvue.web.dto.PostsListResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public List<PostsListResDto> find(Long id) {
        return postRepository.findPostsByUserid(id).stream()
                .map(PostsListResDto::new)
                .collect(Collectors.toList());
    }
}
