package com.codecool.cardhandling.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card {

    @GeneratedValue
    @Id
    private long id;

    private String name;

    private String url;

    private int power;

    private int intelligence;

    private int reflex;

}
