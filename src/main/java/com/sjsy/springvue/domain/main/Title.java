package com.sjsy.springvue.domain.main;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Table(name = "header_title")
@Entity
public class Title {

    private String filePath;

    @Id
    private String fileName;

    private String fileOriginName;

    public Title() {}

    @Builder
    public Title(String fileOriginName, String fileName, String filePath) {
        this.fileOriginName = fileOriginName;
        this.fileName = fileName;
        this.filePath = filePath;
    }

}
