package com.sjsy.springvue.web.dto.response;

import com.sjsy.springvue.domain.board.Board;
import com.sjsy.springvue.domain.board.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import java.util.List;

//게시글 Dto
@Getter
@ToString
public class SidebarResDto {

    private Long categoryId;
    private String categoryName;
    private List<Board> boards;

    @Builder
    public SidebarResDto(Category category, List<Board> boards) {
        this.categoryId = category.getId();
        this.categoryName = category.getCategoryName();
        this.boards = boards;
    }

}
