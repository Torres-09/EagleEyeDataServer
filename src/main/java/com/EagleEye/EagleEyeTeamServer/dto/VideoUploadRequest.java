package com.EagleEye.EagleEyeTeamServer.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VideoUploadRequest {
    private String titleName;
    private LocalDateTime localDateTime;
}