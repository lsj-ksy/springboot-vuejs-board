package com.sjsy.springvue.service.main;

import com.sjsy.springvue.domain.main.Content;
import com.sjsy.springvue.domain.main.ContentFile;
import com.sjsy.springvue.domain.main.ContentRepository;
import com.sjsy.springvue.web.dto.MainFileResDto;
import com.sjsy.springvue.web.dto.MainResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
//대문 Service
public class MainService {

    private final ContentRepository contentRepository;

    @Transactional(readOnly = true)
    public MainResDto mainContent() {

        Content content = contentRepository.mainContent();

        //content 안의 ContentFile을 DTO에 담을 ArrayList
        List<MainFileResDto> contentFileList = new ArrayList<>();

        for(ContentFile contentFile : content.getContentFileList()) {
            MainFileResDto mDto = new MainFileResDto(contentFile);
            contentFileList.add(mDto);
        }

        return MainResDto.builder()
                .content(content)
                .mainFileResDtoList(contentFileList)
                .build();
    }
}
