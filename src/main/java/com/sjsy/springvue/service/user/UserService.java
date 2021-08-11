package com.sjsy.springvue.service.user;

import com.sjsy.springvue.domain.post.PostRepository;
import com.sjsy.springvue.domain.post.PostScrapsRepository;
import com.sjsy.springvue.domain.board.ReplyRepository;
import com.sjsy.springvue.domain.user.User;
import com.sjsy.springvue.domain.user.UserRepository;
import com.sjsy.springvue.web.dto.UserSidebarResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;
    private final PostScrapsRepository postScrapsRepository;

    @Transactional(readOnly = true)
    public UserSidebarResDto user(Long id) {

        User user = userRepository.findUserByUserid(id);

        int postCount = postRepository.countPostsByUserid(id);
        int replyCount = replyRepository.countReplysByUserid(id);
        int scrapCount = postScrapsRepository.countScrapsByUserid(id);

        return UserSidebarResDto.builder()
                .id(user.getId())
                .profileImg(user.getProfileImg())
                .nickname(user.getNickname())
                .createdDate(user.getCreatedDate())
                .socialType(user.getSocialType())
                .postCount(postCount)
                .replyCount(replyCount)
                .scrapCount(scrapCount)
                .build();
    }

}
