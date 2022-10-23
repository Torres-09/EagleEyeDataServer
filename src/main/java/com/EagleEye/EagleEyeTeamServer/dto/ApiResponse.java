package com.EagleEye.EagleEyeTeamServer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse <T>{

    private T data;

    public <T> ApiResponse<T> success(T data){
        return new ApiResponse<>(data);
    }
}
