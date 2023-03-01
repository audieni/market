package com.audieni.market.dtos;

import com.audieni.market.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String email;
    private String country;

    public UserDto(User user) {
        this.email = user.getEmail();
        this.country = user.getCountry();
    }
}

