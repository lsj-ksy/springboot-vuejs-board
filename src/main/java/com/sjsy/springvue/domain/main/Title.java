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

    public Title(String filePath, String fileName, String fileOriginName){
        this.filePath = filePath;
        this.fileName = fileName;
        this.fileOriginName = fileOriginName;
    }

}
