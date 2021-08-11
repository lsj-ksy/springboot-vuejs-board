package com.sjsy.springvue.util;

import com.sjsy.springvue.domain.post.PostFile;
import com.sjsy.springvue.web.dto.PostFileSaveDto;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileHandler {

    //업로드 폴더 경로 고정
    private final String uploadPath;

    public FileHandler() {
        this.uploadPath = System.getProperty("user.dir") + File.separator + "upload";
    }

    //업로드 폴더 생성 여부 체크 함수
    private void checkUploadFolder() {
        if(!new File(uploadPath).exists()) { //업로드 폴더 또는 upload/{folder} 폴더가 없는 경우
            try {
                new File(uploadPath).mkdir(); //폴더 생성
            }catch (Exception e) {
                e.getStackTrace();
            }
        }
    }

    //List<PostFile> 로 변환해주는 메서드
    public List<PostFile> parsePostFileList(List<MultipartFile> fileList, String folderName) throws Exception {

        //결과값 리턴 될 List<PostFile> 생성
        List<PostFile> resultFileList = new ArrayList<>();

        //Apache Commons 라이브러리 중 Null 체크를 해주는 CollectionUtils.isEmpty(객체) 를 사용하는 것이 좋다고 한다.
        if(!CollectionUtils.isEmpty(fileList)) { //fileList가 Null이 아니라면

            // [!]만들어야하는 경로 :: 니컴퓨터/프로젝트폴더/upload/{folderName}/파일저장됨.jpg
            String savePath = uploadPath + File.separator + folderName;

            if(!new File(savePath).exists()) { //업로드 폴더 또는 upload/{folder} 폴더가 없는 경우
                try {
                    checkUploadFolder(); //업로드 폴더 생성 여부 체크 함수 (하단 private)
                    new File(savePath).mkdir(); //폴더 생성
                }catch (Exception e) {
                    e.getStackTrace();
                }
            }

            for(MultipartFile multipartFile : fileList) {
                String fileOriginName = multipartFile.getOriginalFilename(); //ex) 사진.png
                String fileName = new MD5Generator(fileOriginName).toString(); //ex) 0d93jdk21sk....
                String filePath = savePath + File.separator + fileName; //ex) 니컴퓨터/프로젝트폴더/upload/{folderName}/사진.png

                multipartFile.transferTo(new File(filePath)); //transferTo() -> 파일 저장

                //리턴될 결과값 리스트에 추가 (Dto를 새로 만들고 그 Dto의 toEntity() 메서드를 이용하여 PostFile로 만들어줌)
                resultFileList.add(new PostFileSaveDto(fileOriginName, fileName, filePath).toEntity());
            }
        }
        return resultFileList;
    }

}
