/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.vdhbrack.bracet2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vdh24
 */
public class DoubleElim extends BracketMain {
    private final SingleElim WinnersBracket;
    private final LosersBracket LosersBrack;
  
    private GrandFinalsBracket grands;

    public DoubleElim(BracketBuilder b) {
        
         players = b.getPlayerList();
         double sizeConstant=Math.pow(2.0, Math.ceil(Math.log10(players.size()) / Math.log10(2)));
        
        for (int i = players.size(); i < sizeConstant; i++) {
            
             players.add(new DummyPlayer());
        }
        rounds=new ArrayList<Round>();
        WinnersBracket = new SingleElim(players);
        LosersBrack = new LosersBracket();
        
        BracketMain.setSeeds(players);

    }

    @Override
    public Round nextRound() {
        if(rounds.size()>0&&!getMostRecentRound().finished()){
            throw new IllegalStateException("previous round not finished");
        }
         if(readyToFinish()){
            
            throw new IllegalStateException("ready to finish no new round needed");
        }
         
        if(rounds.size()>0&&getMostRecentRound() instanceof SingleElimRound){
            LosersBrack.addPlayers(rounds.get(rounds.size()-1).getLosers());
           
        }
        
        
        Round newt;
        if (WinnersBracket.getPlayersStillIn() > LosersBrack.getPlayersStillIn()) {
            newt=WinnersBracket.nextRound();
        } else if(!LosersBrack.readyToFinish()) {
            newt =LosersBrack.nextRound();
        }
        else{newt=null;
        }
       
        if(LosersBrack.readyToFinish()&&WinnersBracket.readyToFinish()){
         
          
          
            if(grands==null){
                grands=new GrandFinalsBracket(WinnersBracket.finish().get(0),LosersBrack.finish().get(0));
                
            }
            newt= grands.nextRound();
        }
        
        rounds.add(newt);   
        return newt;
    }

    @Override
    public int getPlayersStillIn() {
       return WinnersBracket.getPlayersStillIn()+LosersBrack.getPlayersStillIn();
    }
    
    @Override
    public ArrayList<Player> finish(){
        if(!readyToFinish()){
            throw new IllegalStateException("not ready to finish");
        }
       ArrayList<Player> finat=new ArrayList<>();
       ArrayList<Player> fromLosers=LosersBrack.finish();
       finat.addAll(grands.finish());
       for(int i=1;i<fromLosers.size();i++){
          fromLosers.get(i).setPlacing(fromLosers.get(i).getPlacing()+1);
          finat.add(fromLosers.get(i));
       }
       return finat;
    }
    @Override
    public boolean readyToFinish(){
        return getPlayersStillIn()==1;
    }
}
