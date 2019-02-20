package com.codecool.gamestatus.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class CardServiceResult {

    private String title;
    private int power;
    private int intelligence;
    private int reflex;

}
