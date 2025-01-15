package com.brainstation.fantasyfootball.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * author: Sadik Hassan
 * created: 5:46 pm, 25/10/2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerResponse<T> {
 long count;
 T response;
}
