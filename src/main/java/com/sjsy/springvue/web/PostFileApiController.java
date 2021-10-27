package com.sjsy.springvue.web;

import com.sjsy.springvue.domain.post.PostFileRepository;
import com.sjsy.springvue.service.post.PostFileService;
import com.sjsy.springvue.web.dto.response.PostFileDownloadResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostFileApiController {

    private final PostFileService postFileService;

    //첨부파일 다운로드
    @GetMapping("/api/v1/download/{id}")
    public ResponseEntity<Resource> fileDownload(@PathVariable Long id) throws IOException {
        PostFileDownloadResDto fileDownloadDto = postFileService.getFile(id);
        Path path = Paths.get(fileDownloadDto.getFilePath());
        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDownloadDto.getFileOriginName() + "\"")
                .body(resource);
    }

    @GetMapping("/api/v1/check_file/{id}")
    public ResponseEntity<String> checkFile(@PathVariable Long id) {
        PostFileDownloadResDto fileDownloadDto = postFileService.getFile(id);
        Path path = Paths.get(fileDownloadDto.getFilePath());

        if (path.toFile().isFile()) {
            return new ResponseEntity<String>(String.valueOf(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(String.valueOf(false), HttpStatus.OK);
        }
    }

}
