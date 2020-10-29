/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.vdhbrack.bracet2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author vdh24
 */
public class Runner2 {
     public static void main(String[] args) {
        List<Player> finall=new ArrayList();
        BracketMain main = null;
       
        
        Scanner s = new Scanner(System.in);
        BracketMain.BracketBuilder b = new BracketMain.BracketBuilder();
        System.out.println("to add a player type their name or to get a list of commands type /commands");
        while (!b.getFinished()) {
                
            String z = s.nextLine();
            if (z.startsWith("/commands")) {
                printCommands();
            } else if (z.startsWith("/remove")) {
                b.removePlayer(z.replaceFirst(" ", "").replaceAll("/remove", ""));
            } else if (z.startsWith("/begin")) {
                main = makeBracket(s, b);
               
            } else if (z.startsWith("/print")) {
                System.out.println(b.toString());
            } else if (z.startsWith("/reseed")) {
                System.out.println("What Seed will this person have?");
                Scanner r=new Scanner(System.in);
                int in = r.nextInt();
                b.changeSeed(z.replaceFirst("/reseed", "").replaceAll(" ", ""), in);
            } else if (z.startsWith("/get bracket type")) {
                try{System.out.println(b.getType().toString());}
                catch(RuntimeException e){System.out.println("no bracket type set");}
            } else if (z.startsWith("/change bracket type")) {
                System.out.println("type in either double elimination,round robin or single elimination to change the bracket to that type");
                try {
                    b.changeBracketType(s.nextLine());
                } catch (RuntimeException e) {
                }
            } else {
                b.addPlayer(z);
                System.out.println("player added");
            }
        }
        while (!main.readyToFinish()) {
           
            Round r = main.nextRound();
            
           
            for (int i = 0; i < r.getMatchCount(); i++) {
                
               
                while (r.getMatch(i).getWinner().isEmpty()) {
                    System.out.println(r.getMatch(i));
                    String n = s.nextLine();
                    if (n.replaceAll(" ", "").equals(r.getMatch(i).getFirstPlayer().getName().replaceAll(" ", ""))) {
                        r.getMatch(i).setWinner(r.getMatch(i).getFirstPlayer());
                        
                    }
                   else if (n.replaceAll(" ", "").equals(r.getMatch(i).getSecondPlayer().getName().replaceAll(" ",""))) {
                        r.getMatch(i).setWinner(r.getMatch(i).getSecondPlayer());
                          
                          
                    }
                    else{
                        
                        
                    }
                }

            }
        }
       finall=main.finish();
        for(int f=0;f<finall.size();f++){
        	if(!(finall.get(f) instanceof DummyPlayer)) {
            System.out.println(finall.get(f).getPlacing()+" "+finall.get(f).getName()+" "+finall.get(f).getWins()+"-"+finall.get(f).getLosses());
        	}
        }

    }

    private static BracketMain makeBracket(Scanner s, BracketMain.BracketBuilder b) {
        BracketMain m = null;
        
        try {
            m = b.build();
            System.out.println("T");
            System.out.println(m.toString());
           
            //Change to different type of exception
        } catch (NullPointerException e) {
            System.out.println("what type of bracket will be used");
            try {
                b.changeBracketType(s.nextLine());
                if(!(b.getType()==null)&&b.getType().equals(BracketType.SWISS_SYSTEM)){
                System.out.println("how many rounds");
                b.setSwissRounds(s.nextInt());
                }
            } catch (IllegalStateException r) {
               
            
            	
            }
           
            return makeBracket(s, b);
        }
        System.out.println(m.toString());
        return m;
    }
   

    private static void printCommands() {
        System.out.println("to remove a player type /remove then the player name");
        System.out.println("to print the current player list with seeding type /print");
        System.out.println("to begin the tournament type /begin");
        System.out.println("to reseed a player type /reseed and then the player name ");
        System.out.println("to set a bracket type type /change bracket type");
        System.out.println("to see what type of bracket you have type /get bracket type");

    }
}
