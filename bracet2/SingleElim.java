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
public class SingleElim extends BracketMain {

    public SingleElim(BracketMain.BracketBuilder b) {
        
        dis = BracketType.SINGLE_ELIMINATION;
        players = b.getPlayerList();
        BracketMain.setSeeds(players);
        rounds=new ArrayList();
    }
     SingleElim(List<Player> toUse){
        dis=BracketType.SINGLE_ELIMINATION;
        players=toUse;
        rounds=new ArrayList();
    }

    @Override
    public Round nextRound() {
          
         if(readyToFinish()){
            
            throw new IllegalStateException("ready to finish no new round needed");
        }
        if (rounds.size() <= 0) {

            Round r = new SingleElimRound(players);
            rounds.add(r);
            return r;
        } else {
        	if(!getMostRecentRound().finished()){
                throw new IllegalStateException("previous round not finished");
            }
            List<Player> nextRound = new ArrayList();
            nextRound = getMostRecentRound().returnByes();

            nextRound.addAll(getMostRecentRound().getWinners());
            Round r = new SingleElimRound(nextRound);
            rounds.add(r);
            return r;
        }
    }

    @Override
    public ArrayList<Player> finish() {
        ArrayList<Player> finat = new ArrayList();

        int num = 1;
        if (!this.readyToFinish()) {
            throw new IllegalStateException("Bracket Not Complete");
        } else {
            getMostRecentRound().getWinners().get(0).setPlacing(1);
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

    @Override
    public boolean readyToFinish() {
        return getPlayersStillIn() <= 1;

    }

    @Override
    public int getPlayersStillIn() {
        int counter = 0;
        counter = players.stream().filter((p) -> (p.getLosses() == 0)).map((_item) -> 1).reduce(counter, Integer::sum);
       
        return counter;
    }
}

/**
 *
 * runMatches(); Collections.sort(players); matchround=(int) Math.pow(2.0,
 * Math.ceil(Math.log10(players.size())/Math.log10(2)))+1; for(int
 * i=matchround/2-1;i<players.size();i++) {System.out.println(i);
 * mappy.add(0,players.remove(i));}  *
 * this.rounds.add(newRound); }
 *
 * @Override public void runMatches() { System.out.println("matchround is
 * "+matchround); for(int f=0;f<players.size();f++)
 * {System.out.println(players.get(f).getName()+players.get(f).getISeed());} for
 * (int i=0;i<matchround/2;i++) { for(int k=0;k<players.size();k++) {
 * if(players.get(i).getISeed()+players.get(k).getISeed()==matchround&&!players.get(i).equals(players.get(k)))
 * { match(players.get(i),players.get(k)); } } } } @Override public void
 * finish() { int track=1; System.out.println("1st"+players.get(0).getName());
 * for(int i=0;i<mappy.size();i++) { if((track%10)+1==3)
 * {System.out.println((track+1)+"rd "+mappy.get(i).getName());}
 * else {System.out.println((track+1)+"th "+mappy.get(i).getName());}
 * if(i==track*2) {track*=2;} } }  *
 * @Override public void match(Player a,Player b) {
 * System.out.println(a.toString()+" vs "+b.toString());
 * System.out.println("e"); String next=sc.nextLine(); System.out.println("f");
 * if(next.equals(a.getName())) { a.changeWins(1);b.changeLosses(1);
 * if(a.getISeed()>b.getISeed()) { a.changeISeed(b.changeISeed(a.getISeed())); }
 *
 * }
 * else if(next.equals(b.getName())) {System.out.println(3);
 * b.changeWins(1);a.changeLosses(1); if(b.getISeed()>a.getISeed()) {
 * a.changeISeed(b.changeISeed(a.getISeed()));
 *
 * }
 *
 * }
 * else {System.out.println(5); System.out.println("not a valid player");
 * match(a,b); } }
 */
