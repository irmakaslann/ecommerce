package com.urunsatisi.urunsatisi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class errorResponse {

    private String message;
    private List<String> details;
}
