package com.sjsy.springvue.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HelloController {

    @GetMapping("/api/hello")
    public ResponseEntity<String> hello() {
        String userDirPath = System.getProperty("user.dir");
        String test = userDirPath.substring(0, userDirPath.lastIndexOf("/") + 1);

        String checkPath = System.getProperty("user.dir").substring(0, System.getProperty("user.dir").lastIndexOf("/") + 1);

        System.out.println("this.getClass().getPackage().getName() == "+this.getClass().getPackage().getName());
        System.out.println("this.getClass().getProtectionDomain() == "+this.getClass().getProtectionDomain());

        return new ResponseEntity<String>("hello" + System.getProperty("os.name") + " - " + System.getProperty("user.dir") + " - " + test + " - " + checkPath, HttpStatus.OK);
    }

}
