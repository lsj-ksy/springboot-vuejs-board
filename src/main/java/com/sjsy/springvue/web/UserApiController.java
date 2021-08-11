package com.sjsy.springvue.web;

import com.sjsy.springvue.service.post.PostService;
import com.sjsy.springvue.service.user.UserService;
import com.sjsy.springvue.web.dto.request.UserSidebarReqDto;
import com.sjsy.springvue.web.dto.response.PostsListResDto;
import com.sjsy.springvue.web.dto.response.UserSidebarResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final PostService postService;
    private final UserService userService;

    //나의 활동 받아오기
    @GetMapping("/api/v1/user/myinfo/{id}")
    public UserSidebarResDto user(@PathVariable Long id) {
        return userService.user(id);
    }

    //nickname 중복확인 버튼 click시 중복체크하는 로직
    //닉네임 변경시, front에서 중복확인을 거치지 않았다면 변경이 불가하게 설계할 예정임
    @GetMapping("/api/v1/user/nickname/check/{nickname}")
    public boolean userNicknameCheck(@PathVariable String nickname) {
        boolean check = userService.checkNickname(nickname);

        return check;
    }

    //닉네임변경
    @PatchMapping("/api/v1/user/myinfo/update")
    public void userNicknameUpdate(UserSidebarReqDto userSidebarReqDto) {
        userService.userNicknameUpdate(userSidebarReqDto);
    }


    //이 주소 고민 많이 필요함
    // /api/v1/user/posts/{user.id} 요런느낌?
    @GetMapping("/api/v1/postlist/{id}")
    public List<PostsListResDto> postlist(@PathVariable Long id) {
        return postService.find(id);
    }

}
