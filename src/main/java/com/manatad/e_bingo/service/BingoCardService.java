package com.manatad.e_bingo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.manatad.e_bingo.model.BingoCard;
import com.manatad.e_bingo.model.BingoGame;
import com.manatad.e_bingo.repository.BingoCardRepo;
import com.manatad.e_bingo.repository.BingoGameRepo;

@Service
public class BingoCardService {
    @Autowired
    private BingoCardRepo bingoCardRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private BingoGameRepo bingoGameRepository;

    public BingoCard getBingoCard(String gameCode) {
        BingoGame bingoGame = bingoGameRepository.findByGameCode(gameCode);
        if (bingoGame == null) {
            return null;
        }
        return bingoCardRepository.findByBingoGame(bingoGame);
    }

    public boolean checkWin(String playcardToken) {
        BingoCard bingoCard = bingoCardRepository.findByPlaycardToken(playcardToken);
        if (bingoCard == null) {
            throw new IllegalArgumentException("Bingo card not found");
        }
        String url = "http://www.hyeumine.com/checkwin.php?playcard_token=" + playcardToken;
        int result = restTemplate.getForObject(url, Integer.class);
        return result == 1;
    }
}
