package com.EagleEye.EagleEyeTeamServer.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_id")
    private Long id;

    @Column(name = "video_name")
    private String videoName;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "video_upload_date")
    private LocalDateTime createAt;
}