import React, { useState, useEffect } from 'react';
import './App.css';
import axios from 'axios';

const BINGO_GAME_URL = "http://www.hyeumine.com/bingodashboard.php?bcode=";
const GET_CARD_URL = "http://www.hyeumine.com/getcard.php?bcode=";
const CHECK_WIN_URL = "http://www.hyeumine.com/checkwin.php?playcard_token=";

const App = () => {
  const [players, setPlayers] = useState([]); 
  const [code, setCode] = useState(''); 
  const [error, setError] = useState(''); 

  const getWin = async (player) => {
    try {
      for (const playcardToken of player.arrCode) {
        const url = CHECK_WIN_URL + playcardToken;
        const response = await axios.get(url);

        if (response.data === 0) {
          // Handle the case where the playcard_token is not found
          console.log(`Playcard_token not found for Player ${player.code}:`, playcardToken);
        } else if (response.data === 1) {
          // Set the 'won' property to true for winning cards
          const updatedPlayers = players.map((p) => {
            if (p.code === player.code) {
              const updatedCards = p.cards.map((card) => {
                if (card.token === playcardToken) {
                  return { ...card, won: true };
                }
                return card;
              });
              return { ...p, cards: updatedCards, urlDisplayed: true };
            }
            return p;
          });
          setPlayers(updatedPlayers);
          alert(`Player won! Card token: ${playcardToken}`);
        } else {
          // Set the 'won' property to false for non-winning cards
          const updatedPlayers = players.map((p) => {
            if (p.code === player.code) {
              const updatedCards = p.cards.map((card) => {
                if (card.token === playcardToken) {
                  return { ...card, won: false };
                }
                return card;
              });
              return { ...p, cards: updatedCards, urlDisplayed: true };
            }
            return p;
          });
          setPlayers(updatedPlayers);
          console.log(`Not a winning card for Player ${player.code}:`, playcardToken);
        }
      }
    } catch (error) {
      console.error(`Error checking win for Player ${player.code}:`, error.message);
    }
  };

    Effect(() => {
    const interval = setInterval(() => {
      for (const player of players) {
        if (!player.urlDisplayed) {
          getWin(player);
        }
      }
    }, 1000);
    return () => clearInterval(interval);
  }, [players]);

  const getData = async () => {
    try {
      const response = await axios.get(GET_CARD_URL + code);
      if (response.data === 0) { 
        setError('Game code not found. Please try a different code.');
        return; 
      }
      setError('');
      const card = response.data.card;
      const numbersArray = [card.B, card.I, card.N, card.G, card.O];
      const transposedArray = transpose(numbersArray);
      const playcardToken = response.data.playcard_token;

      setPlayers((prevPlayers) => [
        ...prevPlayers,
        { code, cards: [{ numbers: transposedArray, token: playcardToken, won: false }], arrCode: [playcardToken], urlDisplayed: false },
      ]);
    } catch (err) {
      console.error('Error fetching data:', err.message);
      setError('Failed to fetch data. Please try again later.');
    }
  };

  const transpose = (array) => {  
    return array[0].map((_, colIndex) => array.map(row => row[colIndex]));
  };

  const handleOnClick = () => {
    getData(); 
  };

  const handleGameCodeChange = (e) => {
    setCode(e.target.value); 
  };

  const handleSubmitCode = (e) => {
    e.preventDefault(); 
    getData();
  };

  return (
    <div className="container">
      <h1>Game Code: {code}</h1>
      <form onSubmit={handleSubmitCode}>
        <label htmlFor="gameCode">Enter Game Code: </label>
        <input
          id="gameCode"
          type="text"
          value={code}
          onChange={handleGameCodeChange}
        />
        <button type="submit">Submit Code</button>
      </form>
      {error && <p className="error">{error}</p>} 
      <button onClick={handleOnClick}>Add Card</button>
      <div className="card">
        {players.map((player, playerIndex) => (
          <div key={playerIndex}>
            {player.cards.map((card, cardIndex) => (
              <div
                key={cardIndex}
                className={`bingo-card ${card.won ? 'bingo-won' : ''}`}
              >
                <div className="bingo-header">
                  {['B', 'I', 'N', 'G', 'O'].map((letter, index) => (
                    <div key={index} className="bingo-header-cell">{letter}</div>
                  ))}
                </div>
                {card.numbers.map((column, columnIndex) => (
                  <div key={columnIndex} className="bingo-column">
                    {column.map((number, numberIndex) => (
                      <div key={numberIndex} className="bingo-cell">
                        {number}
                      </div>
                    ))}
                  </div>
                ))}
                <div className="bingo-card-id">Card token: {card.token}</div>
              </div>
            ))}
          </div>
        ))}
      </div>
    </div>
  );
};

export default App;
