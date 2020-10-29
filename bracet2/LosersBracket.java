/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.vdhbrack.bracet2;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author vdh24
 */
public class LosersBracket extends BracketMain {

    private int roundCounter;
    private ArrayList<Player> players;
    private ArrayList<Player> orderedList;
    private ArrayList<ArrayList<Player>> fromWinners;

    public LosersBracket() {
        roundCounter = 0;
        players = new ArrayList<>();
        fromWinners = new ArrayList<>();
        rounds=new ArrayList<>();
    }

    public void addPlayers(ArrayList<Player> p) {
        fromWinners.add(p);
        players.addAll(p);

    }

    @Override
    public Round nextRound() {
         
           if(rounds.size()>0&&!getMostRecentRound().finished()){
        	 
            throw new IllegalStateException("previous round not finished");
        }
         if(readyToFinish()){
            
            throw new IllegalStateException("ready to finish no new round needed");
        }
        ArrayList<Player> temp = new ArrayList<Player>();

        if (rounds.size() == 0 || fromWinners.get(fromWinners.size() - 1).size() == getMostRecentRound().getWinners().size()) {
            temp = fromWinners.get(fromWinners.size() - 1);
        }
        if (rounds.size() > 0) {
            temp.addAll(getMostRecentRound().getWinners());
        }
        Collections.sort(temp);
        for (int i = 0; i < temp.size() / 2; i = i + 2) {
            Player replacemten = temp.set(i + 1, temp.get(i));
            temp.set(i, replacemten);
        }
        LosersRound r = new LosersRound(temp);
        rounds.add(r);
        return r;

    }

    @Override
    public ArrayList<Player> finish() {
        ArrayList<Player> finat = new ArrayList();
        
        int num = 1;
        if (!this.readyToFinish()) {
            throw new IllegalStateException("Bracket Not Complete");
        } else {
            this.getMostRecentRound().getWinners().get(0).setPlacing(1);
            finat.add(getMostRecentRound().getWinners().get(0));
            for (int i = rounds.size() - 1; i >= 0; i--) {

                for (Player p : rounds.get(i).getLosers()) {
                    p.setPlacing(num + 1);
                }
                finat.addAll(rounds.get(i).getLosers());
                num = finat.size();

            }
        }

        return finat;
    }

    public int getPlayersStillIn() {
        int count = 0;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getLosses() < 2) {
               
                count++;
            }
        }
       
        return count;
    }

    public boolean readyToFinish() {
        return getPlayersStillIn() <= 1;
    }

}
