package com.codecool.cardhandling.repository;

import com.codecool.cardhandling.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
