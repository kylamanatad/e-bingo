package com.manatad.e_bingo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.manatad.e_bingo.model.Player;
import com.manatad.e_bingo.service.PlayerService;


@RestController
@RequestMapping("/bingo")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @GetMapping("/getPlayer")
    public ResponseEntity<?> getPlayer(@RequestParam Long playerId) {
        Player player = playerService.getPlayer(playerId);
        if(player != null) {
            return ResponseEntity.ok(player);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player not found");
        }
    }

    @PostMapping("/addPlayer")
    public ResponseEntity<?> addPlayer(@RequestBody Player player) {
        Player addedPlayer = playerService.addPlayer(player);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedPlayer);
    }
}
