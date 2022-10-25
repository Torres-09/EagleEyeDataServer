package com.EagleEye.EagleEyeTeamServer.dto;

import com.EagleEye.EagleEyeTeamServer.entity.Video;
import lombok.Data;

@Data
public class VideoShowDto {

    private String videoUrl;

    public VideoShowDto(Video video) {
        this.videoUrl = video.getVideoUrl();
    }
}
