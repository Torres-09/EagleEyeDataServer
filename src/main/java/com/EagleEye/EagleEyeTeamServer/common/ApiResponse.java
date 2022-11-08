package com.EagleEye.EagleEyeTeamServer.common;

import lombok.*;

import static com.EagleEye.EagleEyeTeamServer.common.ApiResponseStatus.SUCCESS;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class ApiResponse <T>{

    private Boolean isSuccess;
    private String message;
    private String code;
    private T data;

    public static <T> ApiResponse<T> success(T data){
        return new ApiResponse<>(SUCCESS.isSuccess(), SUCCESS.getMessage(), SUCCESS.getCode(), data);
    }

    public static <T> ApiResponse<T> error(ErrorCode errorCode) {
        return new ApiResponse<T>(false , errorCode.getMessage(), errorCode.getCode(),null);
    }
}
