package com.sjsy.springvue.web;

import com.sjsy.springvue.domain.user.User;
import com.sjsy.springvue.service.reply.ReplyService;
import com.sjsy.springvue.web.dto.response.ReplyListResDto;
import com.sjsy.springvue.web.dto.request.ReplySaveReqDto;
import com.sjsy.springvue.web.dto.request.ReplyUpdateReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReplyApiController {

    private final ReplyService replyService;
    // 로그인 했다고 가정
    private final HttpSession httpSession;

    //댓글 등록
    @PostMapping("/api/v1/reply/save")
    public Long replySave(ReplySaveReqDto replySaveReqDto) {
        // 로그인 했다고 가정
        User user = (User) httpSession.getAttribute("user");
        return replyService.replySave(replySaveReqDto);
    }

    //게시물 번호 > 댓글 리스트 가져오기
    @GetMapping("/api/v1/post/{id}/reply/list")
    public ResponseEntity<List<ReplyListResDto>> getReplyList(@PathVariable Long id) {
        return new ResponseEntity<List<ReplyListResDto>>(replyService.getReplyListByPostId(id), HttpStatus.OK);
    }

    //댓글 수정
    @PatchMapping("/api/v1/reply/update/{id}")
    public Long replyUpdate(@PathVariable Long id, ReplyUpdateReqDto replyUpdateReqDto) {
        return replyService.update(id, replyUpdateReqDto);
    }

    //댓글 삭제 (되살리기 0, 1 스위칭 됨)
    @DeleteMapping("/api/v1/reply/delete/{id}")
    public Long delete(@PathVariable Long id) {
        replyService.delete(id);
        return id;
    }

}
