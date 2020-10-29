/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.vdhbrack.bracet2;

import java.util.ArrayList;

/**
 *
 * @author vdh24
 */
public class GrandFinalsBracket extends BracketMain {
    
    public GrandFinalsBracket(Player winners,Player losers){
       players=new ArrayList<>();
       rounds=new ArrayList<>();
        players.add( winners);
        players.add( losers);
    }
    @Override
    public Round nextRound(){
        if(readyToFinish()){
            
            throw new IllegalStateException("ready to finish no new round needed");
        }
         if(readyToFinish()){
            
            throw new IllegalStateException("ready to finish no new round needed");
        }
        
        Round r=new GrandFinalsRound(players.get(0),players.get(1));
        rounds.add(r);
        return r;
    }
    @Override
    public ArrayList<Player> finish(){
        ArrayList<Player> finat=new ArrayList<>();
        if(!readyToFinish()){
        	System.out.println(players.get(0)+" "+players.get(0).getLosses());
        	System.out.println(players.get(1)+" "+players.get(1).getLosses());
            throw new IllegalStateException("Not ready to finish");
        }
        else{
            getMostRecentRound().getMatch(0).getWinner().get().setPlacing(1);
            getMostRecentRound().getMatch(0).getLoser().get().setPlacing(2);
            finat.add(getMostRecentRound().getMatch(0).getWinner().get());
            finat.add(getMostRecentRound().getMatch(0).getLoser().get());
            
        }
        return finat;
    }
    
    @Override
    public int getPlayersStillIn(){
        int counter=0;
        if(players.get(0).getLosses()<2){counter++;}
        if(players.get(1).getLosses()<2){counter++;}
        if(counter==0){
            throw new RuntimeException("No player with less than 2 losses");
        }
        return counter;
    }
    public boolean readyToFinish(){
        return getPlayersStillIn()==1;
    }
}
