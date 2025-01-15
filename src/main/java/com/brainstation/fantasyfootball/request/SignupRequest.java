package com.brainstation.fantasyfootball.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shahinur Beg
 * created date: 10/20/2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignupRequest {
    @JsonProperty(value = "first_name")
    private String firstName;
    @JsonProperty(value = "last_name")
    private String lastName;
    @JsonProperty(value = "username")
    private String username;
    @JsonProperty(value = "email")
    private String email;
    @JsonProperty(value = "password")
    private String password;
}
