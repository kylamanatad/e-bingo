package com.manatad.e_bingo.model;

import jakarta.persistence.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class BingoGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String gameCode;

    @OneToMany(mappedBy = "bingoGame")
    @JsonManagedReference
    private List<Player> players = new ArrayList<>();

    @OneToMany(mappedBy = "bingoGame")
    @JsonManagedReference
    private List<BingoCard> cards = new ArrayList<>();
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getGameCode() {
        return gameCode;
    }
    public void setGameCode(String gameCode) {
        this.gameCode = gameCode;
    }
    public List<Player> getPlayers() {
        return players;
    }
    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    
    
}
