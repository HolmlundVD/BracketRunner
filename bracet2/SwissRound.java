/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.vdhbrack.bracet2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author vdh24
 */
public class SwissRound extends Round {
    public SwissRound(List<Player> p,Optional<DummyPlayer> dummy,int roundNum){
         matches=new ArrayList<Match>();
         if(!dummy.isEmpty()){
             for(int i=p.size()-1;i>=0;i--){
                 if((!p.get(i).getOpp().contains(dummy.get()))&&!(p.get(i) instanceof DummyPlayer)){
                     matches.add(new Match(p.get(i),dummy.get()));break;
                 }
             }
         }
         
         
       for(int i=0;i<p.size();i++){
          
           
          
          for(int j=i+1;j<p.size();j++) {
              if((!p.get(i).getOpp().contains(p.get(j)))&&p.get(i).getOpp().size()==p.get(j).getOpp().size()&&p.get(i).getOpp().size()<roundNum){
           matches.add(new Match(p.get(i),p.get(j)));
              }
          }
          for(int x=p.size()-1;x>=1;x--){
              if(p.get(x).getOpp().size()<roundNum){
                  for(int y=x-1;y>=0;y--){
                      if(p.get(x).getOpp().size()==p.get(y).getOpp().size()){
                          matches.add(new Match(p.get(x),p.get(y)));return;
                      }
                  }
              }
          }
           
       }
       
     
    }
    
}
