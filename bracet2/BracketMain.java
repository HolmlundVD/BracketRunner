/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.vdhbrack.bracet2;

import java.util.*;
/**
 * This abstract class each bracket is built off of.
 * Instances can be constructed by each individual types constructors.
 * It can also be done with the individual constructor in the enum however the 
 * best method is using the get instance method in the builder. the builder class creates a mutable instance of a
 * bracket to set it up. This instance cannot be run.
 * 
 * 
*/
/**
 *
 * @author vdh24
 */
public abstract class BracketMain {
    
    /**
     * public void setupBracket() { System.out.println("to add a player type
     * their name or to get a list of commands type /commands"); String
     * z=sc.nextLine(); if(z.startsWith("/commands")) {printCommands(); } else
     * if(z.startsWith("/remove")) {removePlayer(z.replaceFirst("
     * ","").replaceAll("/remove",""));} else if(z.startsWith("/begin")) {
     * startGame(); } else if(z.startsWith("/print")) {printTournament();} else
     * if(z.startsWith("/reseed"))
     * {changeSeed(z.replaceFirst("/reseed","").replaceAll(" ",""));} else
     * {addPlayer(z);System.out.println("player added");}
     * System.out.println("1"); }
     * @param p
     *
     * this method is meant to be used when the bracket is first initialized(after the building)
     * this takes the player list and sets each players seed to the proper number based on their 
     * order in the list
    */
	/**
	 * 
	 * @param p list of players to be ordered
	 * sets the seed and Iseed of each player to the players index in p+1
	 * used to finalize the bracket for starting
	 */
    protected static void setSeeds(List<Player> p) {
        for (int i = 0; i < p.size(); i++) {
            p.get(i).setISeed(i + 1);
            p.get(i).setSeed(i + 1);
            
        }
    }
    /**
     * list containing all the players
     */
    protected List<Player> players;
    // list containing all rounds
    protected List<Round> rounds; 
    //specifies what type of bracket the instance is of
    protected BracketType dis;

    
    /**
     * 
     * @return whether or not all the matches have been played
     * and a winner is determinable
     */
    public abstract boolean readyToFinish();
    /**
     * 
     * @return return the number of players that are not eliminated from bracket
     * if it is any non advancing bracket such as a round robin it will return the full number of players 
     * even if some have no matches left to play
     */
    public abstract int getPlayersStillIn();
    
    

   
    public List<Player> getPlayerList() {
        return players;
    }
    /**
     * 
     * @return a String representation of the tournament containing each player
     * separated by line with their seed and name
     */
    @Override
    public String toString() {
        String str = new String();
        for (int i = 0; i < players.size(); i++) {
            str += ((i + 1) + " " + players.get(i).getName() + "\n");
        }
        return str;
    }


    
    
    /**
     * 
     * @return an ordered list of the players with each ones placing set
     * @throws IllegalStateException if bracket is not ready to finish
     */
    public abstract List<Player> finish();
    /**
     * 
     * @return a next round for the bracket 
     * @throws IllegalStateException if all matches in the previous round are not complete
     * or readyToFinish returns true
     */
    public abstract Round nextRound();

    public Round getRound(int round) {
        return rounds.get(round);
    }

    public Round getMostRecentRound() {
        return rounds.get(rounds.size() - 1);
    }

    
       
    public static class BracketBuilder {
        
        
        private final ArrayList<Player> p;
        private int swissRounds=0;
        private BracketType type;
        private boolean finished=false;
        public BracketBuilder() {
            p = new ArrayList();
        }

        public void addPlayer(String name) {
        	String nameToUse=name.trim();
        	for(Player players:p) {
        		if(players.getName().equals(name)) {
        			throw new IllegalArgumentException(name+"is already in the bracket");
        		}
        		
        	}
            
            p.add(new Player(name));
        }

        public void changeSeed(String name, int newSeed) {

            for (int i = 0; i < p.size(); i++) {

                if (p.get(i).getName().replaceAll(" ", "").equals(name)) {

                    p.set(newSeed - 1, p.set(i, p.get(newSeed - 1)));
                    break;
                }
            }

        }

        public void removePlayer(String name) {
            for (int i = 0; i < p.size(); i++) {
                if (p.get(i).getName().equals(name)) {
                    p.remove(p.get(i));
                }
            }
        }
        public void setSwissRounds(int r){
            swissRounds=r;
        }
        public int getSwissRounds(){
            return swissRounds;
        }

        

       

       
        
       
        @Override
        public String toString() {
             String str = new String();
        for (int i = 0; i < p.size(); i++) {
            str += ((i + 1) + " " + p.get(i).getName() + "\n");
        }
        return str;
            
            
        }
        //returns null if none of the cases are met
        public BracketMain build() {
            finished=true;
            return type.getInstance(this);
        }
        public boolean getFinished(){
            return finished;
        }

        public void changeBracketType(String nextLine) {
            try{
            type=BracketType.valueOf(nextLine.toUpperCase().replaceAll(" ", "_"));
            }catch(IllegalArgumentException e){
                         }
        }

        public BracketType getType() {
            return type;
        }
        public List<Player> getPlayerList(){
            return new ArrayList<Player>(p);
        }

       
        
        
    }

}
