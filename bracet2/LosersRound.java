/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.vdhbrack.bracet2;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author vdh24
 */
public class LosersRound extends Round {

    LosersRound(ArrayList<Player> p) {
        matches=new ArrayList();
        
        
        for(int i=0;i<p.size()/2;i++){
            matches.add(new Match(p.get(i),p.get(p.size()-1-i)));
            
        }
        

    }

}
