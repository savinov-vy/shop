package ru.savinov.shop.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UploadResponseDto {
    private final String fileName;
    private final String title;
    private final String uploadDate;
}