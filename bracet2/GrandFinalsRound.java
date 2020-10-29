/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.vdhbrack.bracet2;

import java.util.ArrayList;

/**
 *
 * @author vdh24
 */
public class GrandFinalsRound extends Round {
    public GrandFinalsRound(Player a,Player b){
        matches=new ArrayList<>();
        matches.add(new Match(a, b));
       
    }
    
    
}
