/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.vdhbrack.bracet2;

/**
 *
 * @author vdh24
 */
import java.util.Optional;

public class Match {

    private final Player first;
    private final Player second;
    private Player winner;
    private Player loser;

    public Match(Player a, Player b) {
        first = a;
        second = b;
        a.addOpponent(b);
        b.addOpponent(a);
        if (first instanceof DummyPlayer) {
            setWinner(second);
        }
        if (second instanceof DummyPlayer) {
            setWinner(first);
        }
    }

    public Player getFirstPlayer() {
        return first;
    }

    public Player getSecondPlayer() {
        return second;
    }
    public boolean played(){
     return winner!=null&&loser!=null;   
    }

    public Optional<Player> getWinner() {
        return Optional.ofNullable(winner);

    }

    public Optional<Player> getLoser() {
        return Optional.ofNullable(loser);
    }

    public void setWinner(Player a) {
      
         
            if (winner != a) {
                if (a.equals(first)) {
                    winner = first;
                    loser = second;
                } else if (a.equals(second)) {
                    winner = second;
                    loser = first;
                } else {
                    throw new RuntimeException("player is not in this match");
                }
                winner.changeWins(1);
                loser.changeLosses(1);
            }
        
       
        tradeISeedsIfIncorrect();
    }

    public void tradeISeedsIfIncorrect() {
      
        
        if (winner.getISeed() > loser.getISeed()) {
            winner.setISeed(loser.setISeed(winner.getISeed()));
        }
    }

    @Override
    public String toString() {
        return first.toString() + " vs " + second.toString();
    }
}
