package com.sjsy.springvue.service.user;

import com.sjsy.springvue.domain.post.PostRepository;
import com.sjsy.springvue.domain.post.PostScrapsRepository;
import com.sjsy.springvue.domain.board.ReplyRepository;
import com.sjsy.springvue.domain.user.User;
import com.sjsy.springvue.domain.user.UserRepository;
import com.sjsy.springvue.web.dto.request.UserSidebarReqDto;
import com.sjsy.springvue.web.dto.response.UserSidebarResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;
    private final PostScrapsRepository postScrapsRepository;

    //나의 활동 response service
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

    //닉네임 중복 check service
    @Transactional(readOnly = true)
    public boolean checkNickname(String nickname) {
        int result = userRepository.findNickname(nickname);

        if(result == 0) { //닉네임으로 조회된 값이 있다면 중복아님, true
            return true;
        } else { //닉네임으로 조회된 값이 없다면 중복아님, false
            return false;
        }
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

}
