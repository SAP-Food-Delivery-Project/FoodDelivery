package org.example.fooddelivery.entities.dtos.UserDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoWithIdAndEmail {

    private int id;

    private String email;

}
