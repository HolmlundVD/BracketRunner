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
import java.util.*;

public abstract class Round  {
    

    protected List<Match> matches;
    protected List<Player> byes;
    public boolean finished(){
        for(Match m:matches){
            if(!m.played()){
            	
                return false;
            }
        }
        return true;
    }

    /**
     * Collections.sort(players); matchround=(int) Math.pow(2.0,
     * Math.ceil(Math.log10(players.size())/Math.log10(2)))+1; for (int
     * i=0;i<matchround/2;i++) { for(int k=0;k<players.size();k++) {
     * if(players.get(i).getISeed()+players.get(k).getISeed()==matchround&&!players.get(i).equals(players.get(k)))
     * { matches.add(new Match(players.get(i),players.get(k))); } } }
     *
     */
    public int getMatchCount() {
        
        return matches.size();
    }

    public Match getMatch(int i) {
        return matches.get(i);
    }
    //returns a list of the match winners as well as bies if there are any
    //throws a no such value exception if winners haven't been choosen for everymatch

    public ArrayList<Player> getWinners() {
        ArrayList<Player> wins = new ArrayList();
        for (Match m : matches) {
            wins.add(m.getWinner().get());
        }
        return wins;
    }
    

    public ArrayList<Player> getLosers() {
        ArrayList<Player> losers = new ArrayList();
        for (Match m : matches) {
            losers.add(m.getLoser().get());
        }
        return losers;
    }

    public List<Player> returnByes() {
        return byes;
    }
    public String toString() {
    	StringBuilder builder=new StringBuilder();
    	for(Match m:matches) {
    		builder.append(m+" \n");
    	}
    	return builder.toString();
    }

}
