package com.brainstation.fantasyfootball.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {
    private String eventName;
    private String eventDescription;
}
