package com.manatad.e_bingo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.manatad.e_bingo.model.BingoCard;
import com.manatad.e_bingo.service.BingoCardService;

@RestController
@RequestMapping("/bingo")
public class BingoCardController {
    @Autowired
    private BingoCardService bingoCardService;

    @GetMapping("/getcard")
    public ResponseEntity<?> getCard(@RequestParam String bcode) {
        BingoCard bingoCard = bingoCardService.getBingoCard(bcode);
        if (bingoCard == null) {
            return ResponseEntity.ok("0");
        } else {
            return ResponseEntity.ok(bingoCard);
        }
    }

    @GetMapping("/check-win")
    public ResponseEntity<?> checkWin(@RequestParam String playcardToken) {
        boolean isWin = bingoCardService.checkWin(playcardToken);
        if (isWin) {
            return ResponseEntity.ok("Winning card");
        } else {
            return ResponseEntity.ok("Not a winning card");
        }
    }
}
