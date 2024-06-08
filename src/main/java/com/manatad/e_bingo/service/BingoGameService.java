package com.manatad.e_bingo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manatad.e_bingo.model.BingoGame;
import com.manatad.e_bingo.repository.BingoGameRepo;

@Service
public class BingoGameService {
    @Autowired
    private BingoGameRepo bingoGameRepository;

    public BingoGame getBingoGame(String gameCode) {
        return bingoGameRepository.findByGameCode(gameCode);
    }
}
