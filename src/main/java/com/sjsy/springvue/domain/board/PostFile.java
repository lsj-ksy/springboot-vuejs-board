package com.sjsy.springvue.domain.board;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "post_file")
@Entity
public class PostFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;

    private String fileOriginName;

    private String fileName;

    private String filePath;

    @Builder
    public PostFile(Post post, String fileOriginName, String fileName, String filePath) {
        this.post = post;
        this.fileOriginName = fileOriginName;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    //Post 설정하기
    public void setPostInfo(Post post) {
        this.post = post;

        //파라미터로 들어온 post의 postFileList에 해당(this) postFile 이 들어있지 않다면
        if(!post.getPostFileList().contains(this))
            post.getPostFileList().add(this);
    }

}
