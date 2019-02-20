package com.codecool.gamestatus.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class CardServiceResult {

    private int id;
    private String title;
    private String url;
    private int power;
    private int intelligence;
    private int reflex;

}
