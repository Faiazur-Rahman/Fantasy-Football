package com.brainstation.fantasyfootball.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shahinur Beg
 * created date: 10/20/2022
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public interface UserDto {
    Long getId();
    String getUsername();
    String getPassword();
    boolean getEnabled();
    String getFirst_name();
    String getLast_name();
    String getEmail();
}
