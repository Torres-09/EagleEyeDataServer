package com.EagleEye.EagleEyeTeamServer.common;

import lombok.Getter;

@Getter
public enum ApiResponseStatus {

    SUCCESS(true, "1000", "요청성공");
    private final boolean isSuccess;
    private final String code;
    private final String message;

    ApiResponseStatus(boolean isSuccess, String code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }


}
