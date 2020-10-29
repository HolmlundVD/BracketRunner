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
public class RoundRobinRound extends Round {

    public RoundRobinRound(List<Player> p) {
      
        matches = new ArrayList();
        byes = new ArrayList();
        if (p.size() % 2 == 0) {
            for (int i = 0; i < p.size() / 2; i++) {

                matches.add(new Match(p.get(i), p.get(p.size() - i - 1)));
            }
        } else {
            for (int i = 0; i < p.size() / 2; i++) {
                matches.add(new Match(p.get(i), p.get(p.size() - i - 2)));
                byes.add(p.get(p.size() - 1));
            }
        }
    }

}
