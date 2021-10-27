package com.sjsy.springvue.error;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ErrorResponse {

    private String message;
    private String code;
    private int status;
    private List<errorDetail> detail = new ArrayList<>();

    @Builder
    public ErrorResponse(String message, String code, int status, List<errorDetail> detail) {
        this.message = message;
        this.code = code;
        this.status = status;
        this.detail = initDetail(detail);
    }

    private List<errorDetail> initDetail(List<errorDetail> detail) {
        return (detail == null) ? new ArrayList<>() : detail;
    }

    @Getter
    public static class errorDetail {
        private String field;
        private String value;
        private String reason;
        private LocalDateTime time;

        @Builder
        public errorDetail(String field, String value, String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
            this.time = LocalDateTime.now();
        }
    }

}
