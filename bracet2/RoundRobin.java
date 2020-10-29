/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.vdhbrack.bracet2;

import java.util.*;

/**
 *
 * @author vdh24
 */
public class RoundRobin extends BracketMain {

   

    

    public static void shift(LinkedList<Player> p) {
    	Player first=p.pop();
    	
        Player temp=p.pop();
        p.addFirst(first);
        p.addLast(temp);
        
    }
    public RoundRobin(BracketMain.BracketBuilder b) {
        List<Player> p=b.getPlayerList();
        if (p.size() % 2 == 1) {
            p.add(new DummyPlayer());
        }
        dis = BracketType.ROUND_ROBIN;
        rounds=new ArrayList();
        players = new LinkedList(p);
    }
    @Override
    public Round nextRound() {
    	 if (rounds.size() > 0) {
    		 
             shift((LinkedList<Player>) players);
         }else {
        	 Round r = new RoundRobinRound(players);
             rounds.add(r);
             return r;
         }
          if(!getMostRecentRound().finished()){
            throw new IllegalStateException("previous round not finished");
        }
         if(readyToFinish()){
            
            throw new IllegalStateException("ready to finish no new round needed");
        }
       
       
        Round r = new RoundRobinRound(players);
        rounds.add(r);
        return r;
        
    }

    @Override
    public boolean readyToFinish() {
      
       return (players.size() % 2 == 0 && rounds.size() == players.size() - 1);

    }

    @Override
    public List<Player> finish() {
        List<Player> finat=players;
        if (!readyToFinish()) {
            throw new IllegalStateException("Bracket Not Completed");
        }

        Collections.sort(finat,Tiebreaker::compareByWins);
        finat.get(0).setPlacing(1);
        for (int i = 1; i < finat.size(); i++) {
            if (finat.get(i).getLosses() == finat.get(i - 1).getLosses()) {
                finat.get(i).setPlacing(finat.get(i - 1).getPlacing());
            } else {
                finat.get(i).setPlacing(i + 1);
            }
        }
        return finat;
    }

    @Override
    public int getPlayersStillIn() {
        return (players.size() / 2) * 2;
    }

}
