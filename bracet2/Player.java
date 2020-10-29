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
public class Player implements Comparable<Player> {

    private final String namen;
    private int seeds;
    private int iseed;
    private int wins;
    private int losses;
    private int ties;
    private int placing;
    ArrayList<Player> opp;

    public Player(String name) {
        namen = name;
        opp = new ArrayList();
    }

    public String getName() {
        String s = namen;
        return s;
    }

    public int getSeed() {
        return seeds;
    }

    public int getISeed() {
        return iseed;
    }

    public void setSeed(int newSeed) {
        seeds = newSeed;
    }

    public int setISeed(int newSeed) {
        int temp = iseed;
        iseed = newSeed;
        return temp;

    }

    public void setWins(int i) {
        wins = i;
    }

    public void changeWins(int i) {
        wins += i;
    }

    public void setLosses(int i) {
        losses = i;
    }

    public void changeLosses(int i) {
        losses += i;
    }

    public void setTies(int i) {
        ties = i;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setPlacing(int placin) {
        placing = placin;
    }

    public int getPlacing() {
        return placing;
    }

    public double getRecord() {
        return (wins + 0.5 * ties) / wins + ties + losses;
    }

    public void addOpponent(Player p) {
        opp.add(p);
    }

    public ArrayList<Player> getOpp() {
        return opp;
    }

    @Override
    public int compareTo(Player p) {
         
            return Integer.compare(iseed, p.getISeed());
        
    }

    
    @Override
    public String toString() {
        return seeds +" " + namen;
    }
}
