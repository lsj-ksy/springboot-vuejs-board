package com.sjsy.springvue.service.main;

import com.sjsy.springvue.domain.board.PostFile;
import com.sjsy.springvue.domain.board.PostRepository;
import com.sjsy.springvue.domain.main.*;
import com.sjsy.springvue.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.sjsy.springvue.util.FileHandler;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
//대문 Service
public class MainService {

    private final ContentRepository contentRepository;
    private final ContentFileRepository contentFileRepository;
    private final PostRepository postRepository;
    private final TitleRepository titleRepository;
    private final FileHandler fileHandler;

    //Title 사진 response service
    @Transactional(readOnly = true)
    public MainTitleResDto mainTitle() {
        return new MainTitleResDto(titleRepository.findTitleFile());
    }


    //Title 사진 등록
    @Transactional
    public void titlePut(MultipartFile file, TitleFileSaveReqDto titleFileSaveReqDto) throws Exception {
            //title은 하나이므로 전에있던 파일들 삭제
            titleRepository.deleteAll();

            //util에 직접 만들어준 fileHandler 로 파일을 업로드하는 메소드, upload 폴더 안에 만들어질 폴더 이름 넣어줘야 함
            Title titleFile = fileHandler.parseTitle(file, "titlefile");
            titleRepository.save(titleFile);
    }

    //main과 main files service response service
    @Transactional(readOnly = true)
    public MainResDto mainContent() {

        Content content = contentRepository.mainContent();

        //content 안의 ContentFile을 DTO에 담을 ArrayList
        List<MainFileResDto> contentFileList = new ArrayList<>();

        for (ContentFile contentFile : content.getContentFileList()) {
            MainFileResDto mDto = new MainFileResDto(contentFile);
            contentFileList.add(mDto);
        }

        return MainResDto.builder()
                .content(content)
                .mainFileResDtoList(contentFileList)
                .build();
    }


    //main 게시물 등록
    @Transactional
    public Long mainSave(Optional<List<MultipartFile>> fileList, MainSaveReqDto mainSaveReqDto) throws Exception {
        //Dto의 toEntity() 메서드를 통해 Post 타입으로 만들어주고
        Content saveContent = mainSaveReqDto.toEntity(mainSaveReqDto);

        if (fileList.isPresent()) { //fileList가 Null이 아니라면 (첨부된 파일이 있다면)

            //util에 직접 만들어준 fileHandler 안에 List<Content>로 바꿔주는 메서드, 그리고 upload 폴더 안에 만들어질 폴더 이름 넣어줘야 함
            List<ContentFile> contentFileList = fileHandler.parseContentFileList(fileList.get(), "mainfile");

            //forEach 돌면서(파일이 1개이든 다중파일이든) contentFile 저장됨
            contentFileList.forEach(contentFile -> saveContent.addContentFile(contentFileRepository.save(contentFile)));
        }
        return contentRepository.save(saveContent).getId(); //post 저장 후 getId 하여 id값 리턴
    }


    //main 전체글보기 dto response service
    @Transactional(readOnly = true)
    public List<PostsListResDto> findAllByEnabled() {
        return postRepository.findAllByEnabled().stream()
                .map(PostsListResDto::new)
                .collect(Collectors.toList());
    }


}
