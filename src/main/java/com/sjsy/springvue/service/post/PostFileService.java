package com.sjsy.springvue.service.post;

import com.sjsy.springvue.domain.post.PostFile;
import com.sjsy.springvue.domain.post.PostFileRepository;
import com.sjsy.springvue.web.dto.response.PostFileDownloadResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostFileService {

    private final PostFileRepository postFileRepository;

    @Transactional(readOnly = true)
    public PostFileDownloadResDto getFile(Long id) {
        PostFile entity = postFileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not Found PostFile id = " + id));
        return new PostFileDownloadResDto(entity);
    }

}
