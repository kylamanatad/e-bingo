package com.manatad.e_bingo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manatad.e_bingo.model.BingoCard;
import com.manatad.e_bingo.model.BingoGame;

public interface BingoCardRepo extends JpaRepository<BingoCard, Long> {
    BingoCard findByPlaycardToken(String playcardToken);
    BingoCard findByBingoGame(BingoGame bingoGame);
}

