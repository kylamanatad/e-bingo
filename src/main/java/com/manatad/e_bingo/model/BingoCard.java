package com.manatad.e_bingo.model;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
public class BingoCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String playcardToken;

    @ManyToOne
    @JoinColumn(name = "player_id")
    @JsonBackReference
    private Player player;

    @ManyToOne
    @JoinColumn(name = "bingo_game_id")
    @JsonBackReference
    private BingoGame bingoGame;

    @ElementCollection
    private Map<String, List<Integer>> card = new HashMap<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaycardToken() {
        return playcardToken;
    }

    public void setPlaycardToken(String playcardToken) {
        this.playcardToken = playcardToken;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Map<String, List<Integer>> getCard() {
        return card;
    }

    public void setCard(Map<String, List<Integer>> card) {
        this.card = card;
    }
    
    
}
