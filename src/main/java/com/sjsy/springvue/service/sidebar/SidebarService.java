package com.sjsy.springvue.service.sidebar;

import com.sjsy.springvue.domain.board.BoardRepository;
import com.sjsy.springvue.domain.board.Category;
import com.sjsy.springvue.domain.board.CategoryRepository;
import com.sjsy.springvue.web.dto.response.SidebarResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SidebarService {

    private final CategoryRepository categoryRepository;
    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public List<SidebarResDto> findSidebarBoards() {
        //카테고리리스트
        List<Category> categories = categoryRepository.findAllByEnabled();

        List<SidebarResDto> sidebarResDto = new ArrayList<SidebarResDto>();

        categories.forEach(category -> sidebarResDto.add(SidebarResDto.builder()
                .category(category)
                .boards(boardRepository.findByCategoryId(category.getId()))
                .build()));

        return sidebarResDto;
    }
}
