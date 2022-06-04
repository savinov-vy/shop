package ru.savinov.shop.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.savinov.shop.services.FileStorageService;
import ru.savinov.shop.controllers.dto.UploadResponseDto;

@RestController
@AllArgsConstructor
public class FileUploadController {
    private final FileStorageService fileStorageService;

    @PostMapping("/upload")
    public ResponseEntity<UploadResponseDto> uploadFile(
            @RequestParam(name = "file", required = false) MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("uploadDate") String uploadDate
    ) {
        String fileName = fileStorageService.storeFile(file);
        UploadResponseDto uploadResponseDto = new UploadResponseDto(fileName, title, uploadDate);
        return ResponseEntity.ok().body(uploadResponseDto);
    }
}