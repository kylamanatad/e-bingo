package com.manatad.e_bingo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.manatad.e_bingo.model.Player;

public interface PlayerRepo extends JpaRepository<Player, Long> {

    
}
