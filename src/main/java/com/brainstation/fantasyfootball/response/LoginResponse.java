package com.brainstation.fantasyfootball.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Shahinur Beg
 * created date: 10/20/2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class LoginResponse {
    private String token;
}
