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
public class SingleElimRound extends Round {

    int matchround;

    public SingleElimRound(List<Player> players) {
        matches = new ArrayList();
        byes = new ArrayList();
        Collections.sort(players);
        matchround = (int) Math.pow(2.0, Math.ceil(Math.log10(players.size()) / Math.log10(2))) + 1;
        for (int i = 0; i < matchround / 2; i++) {
            for (int k = 0; k < players.size(); k++) {
                if (players.get(i).getISeed() + players.get(k).getISeed() == matchround && !players.get(i).equals(players.get(k))) {
                    matches.add(new Match(players.get(i), players.get(k)));
                }
            }
        }
        for (int i = 0; i < matchround / 2 - (players.size() - matchround / 2); i++) {
            byes.add(players.get(i));
        }
    }
    private ArrayList<Player> ProperlySort(ArrayList<Player> p)
    {Collections.sort(p);
    return p;
        
    }        
    
}
