package com.sjsy.springvue.service.reply;

import com.sjsy.springvue.domain.post.Post;
import com.sjsy.springvue.domain.post.PostRepository;
import com.sjsy.springvue.domain.board.Reply;
import com.sjsy.springvue.domain.board.ReplyRepository;
import com.sjsy.springvue.domain.user.User;
import com.sjsy.springvue.domain.user.UserRepository;
import com.sjsy.springvue.web.dto.ReplyListResDto;
import com.sjsy.springvue.web.dto.ReplySaveReqDto;
import com.sjsy.springvue.web.dto.ReplyUpdateReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    // (댓글 등록) user_id로 User 가져오기
    private User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not Found User id = " + id));
    }

    // (댓글 등록) post_id로 Post 가져오기
    private Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not Found Post id = " + id));
    }

    //댓글 등록
    @Transactional
    public Long replySave(ReplySaveReqDto replySaveReqDto) {
        return replyRepository.save(replySaveReqDto.toEntity(
                getUserById(Long.parseLong(replySaveReqDto.getUserId())),
                getPostById(Long.parseLong(replySaveReqDto.getPostId()))
        )).getId();
    }

    @Transactional(readOnly = true)
    public List<ReplyListResDto> getReplyListByPostId(Long id) {
        return postRepository.findById(id).get().getReplyList().stream()
                .map(ReplyListResDto::new)
                .collect(Collectors.toList());
    }

    //댓글 수정
    @Transactional
    public Long update(Long id, ReplyUpdateReqDto replyUpdateReqDto) {
        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not Found Reply id = " + id));

        if(!String.valueOf(reply.getUser().getId()).equals(replyUpdateReqDto.getUserId())) {
            throw new IllegalArgumentException("No Match User id = [Request] " + replyUpdateReqDto.getUserId() + " & [Response] " + reply.getUser().getId().toString());
        }
        return id;
    }


}
