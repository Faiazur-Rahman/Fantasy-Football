package com.brainstation.fantasyfootball.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinAnEventDTO {
    private Long id;
    private String eventCode;
}
