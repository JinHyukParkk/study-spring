package com.example.springcloud.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FileController {

    @Autowired
    FileService fileService;

    @GetMapping("/upload/image")
    public ResponseEntity fileupload() {
        Map<String, Object> data = new HashMap<>();
        data.put("userId", "홍길동");
        data.put("type", "webp");
        data.put("fileSize", 5);

        fileService.fileUpload(data);

        return ResponseEntity.ok("success");
    }

}
