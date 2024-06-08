package com.manatad.e_bingo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manatad.e_bingo.model.BingoGame;

public interface BingoGameRepo extends JpaRepository<BingoGame, Long> {

    BingoGame findByGameCode(String gameCode);
} 
