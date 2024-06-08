package com.manatad.e_bingo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manatad.e_bingo.model.Player;
import com.manatad.e_bingo.repository.PlayerRepo;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepo playerRepository;

    public Player getPlayer(Long playerId) {
        return playerRepository.findById(playerId).orElse(null);
    }

    public Player addPlayer(Player player) {
        return playerRepository.save(player);
    }
}
