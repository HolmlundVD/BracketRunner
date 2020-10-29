/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.vdhbrack.bracet2;

import java.util.List;

/**
 *
 * @author vdh24
 */
public enum BracketType {
    ROUND_ROBIN{@Override
    public BracketMain getInstance(BracketMain.BracketBuilder b){return new RoundRobin(b);}},
   SWISS_SYSTEM{@Override
   public BracketMain getInstance(BracketMain.BracketBuilder b){return new SwissBracket(b);}},
   DOUBLE_ELIMINATION{@Override
   public BracketMain getInstance(BracketMain.BracketBuilder b){return new DoubleElim(b);}}
   ,SINGLE_ELIMINATION{@Override
   public BracketMain getInstance(BracketMain.BracketBuilder b){return new SingleElim(b);} };
   
   public abstract BracketMain getInstance(BracketMain.BracketBuilder b);
}
