package com.korit.sinicare.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 서버 정상작동 확인용
@RestController
@RequestMapping("/")

public class MainController {
    
    

    @GetMapping("")
    public String main() {
        return "Server on...";
    }

    @PostMapping("test")
    public String test(
        @RequestBody String name
    ) {
        
        
        return null;
    }
}
