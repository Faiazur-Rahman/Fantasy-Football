package com.brainstation.fantasyfootball.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ServiceResponse<T> {
    private boolean hasError;
    private boolean exist;
    private boolean authorization;
    private T content;
    private String errorMsg;
    private String successMsg;
}
