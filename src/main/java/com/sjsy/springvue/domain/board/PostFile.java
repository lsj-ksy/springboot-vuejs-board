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

    @NotNull
    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;

    private String originFileName;

    private String fileName;

    private String filePath;

    @Builder
    public PostFile(Post post, String originFileName, String fileName, String filePath) {
        this.post = post;
        this.originFileName = originFileName;
        this.fileName = fileName;
        this.filePath = filePath;
    }

}