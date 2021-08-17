package com.sjsy.springvue.web;

import com.sjsy.springvue.config.auth.dto.SessionDto;
import com.sjsy.springvue.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class HelloController {

    private final HttpSession httpSession;
    private final UserService userService;

    @GetMapping("/api/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<String>("hello", HttpStatus.OK);
    }

    @GetMapping("/api/test/login/{id}")
    public String testLogin(@PathVariable Long id) {
        httpSession.setAttribute("user", new SessionDto(userService.testLogin(id)));
        return "redirect:/";
    }
}
