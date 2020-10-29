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
public class SwissBracket extends BracketMain {
   
    private int roundNum;
    private Optional<DummyPlayer> containsDummy;
    
    public SwissBracket(BracketBuilder b) {
        players = new ArrayList<Player>(b.getPlayerList());
        if(b.getSwissRounds()>=players.size()||b.getSwissRounds()<1){
           
            throw new IllegalStateException("swiss will not work with more or equal rounds than there are players");
            
        }
        roundNum=b.getSwissRounds();
        rounds = new ArrayList<Round>();
        if(players.size()%2==1){
            DummyPlayer dummy=new DummyPlayer();
            players.add(dummy);
            containsDummy=Optional.of(dummy);
            
        }
        else{
            containsDummy=Optional.empty();
        }
    }

    @Override
    public Round nextRound() {
          if(rounds.size()>0&&!getMostRecentRound().finished()){
            throw new IllegalStateException("previous round not finished");
        }
         if(readyToFinish()){
            
            throw new IllegalStateException("ready to finish no new round needed");
        }
        Collections.sort(players);
        Round r=new SwissRound(players,containsDummy,rounds.size()+1);
        rounds.add(r);
        return r;
    }

   

    public int getNumberOfRounds() {
        return roundNum;
    }
    @Override
    public int getPlayersStillIn(){
        return (players.size()/2)*2;
    }
    

    @Override
    public List<Player> finish() {
        List<Player>finat=players;
         if (!readyToFinish()) {
            throw new IllegalStateException("Bracket Not Completed");
        }

        Collections.sort(finat,(x,y)->Integer.compare(y.getWins(),x.getWins()));
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
    public boolean readyToFinish() {
        
        return rounds.size() == roundNum;
    }

}
