package com.EagleEye.EagleEyeTeamServer.repository;

import com.EagleEye.EagleEyeTeamServer.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

    Video findVideoById(Long videoId);

}
