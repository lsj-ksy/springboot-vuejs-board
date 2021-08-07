package com.sjsy.springvue.web.dto;

import com.sjsy.springvue.domain.board.Board;
import com.sjsy.springvue.domain.user.User;

public class PostSaveReqDto {

    private User user;
    private Board board;
    private String subject;
    private String content;

}
