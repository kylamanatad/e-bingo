package com.manatad.e_bingo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.manatad.e_bingo.model.BingoGame;
import com.manatad.e_bingo.service.BingoGameService;

@RestController
@RequestMapping("/bingo")
public class BingoGameController {
    @Autowired
    private BingoGameService bingoGameService;

    @GetMapping("/getGame")
    public ResponseEntity<?> getBingoGame(@RequestParam String gameCode) {
        BingoGame bingoGame = bingoGameService.getBingoGame(gameCode);
        if (bingoGame != null) {
            return ResponseEntity.ok(bingoGame);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bingo game not found");
        }
    }

    
}