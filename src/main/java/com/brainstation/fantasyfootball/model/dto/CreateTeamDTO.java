package com.brainstation.fantasyfootball.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTeamDTO {
    private List<String> selectedPlayerId;
    private List<String> selectedPosition;
    private String updatedPoint;
    private String formation;
}
