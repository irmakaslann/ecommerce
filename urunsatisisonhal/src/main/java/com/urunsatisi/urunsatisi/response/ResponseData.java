package com.urunsatisi.urunsatisi.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData {
    private String fileName;
    private String fileType;
    private long fileSize;

    public ResponseData(String fileName, String downloadURL, String contentType, long size) {
    }
}
