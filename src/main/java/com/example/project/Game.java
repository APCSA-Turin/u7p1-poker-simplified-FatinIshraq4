package com.example.project;
import java.util.ArrayList;


public class Game{
    public static String determineWinner(Player p1, Player p2,String p1Hand, String p2Hand,ArrayList<Card> communityCards){
        //Find numerical value of hand 
        int p1Value = Utility.getHandRanking(p1Hand);
        int p2Value = Utility.getHandRanking(p2Hand);
        //Determine winner based on which player has the higher value
        if(p1Value>p2Value) {return "Player 1 wins!";}
        if(p2Value>p1Value) {return "Player 2 wins!";}
        
        //This looks for the higher card / set of cards when both players have the same hand 
        if (p1Value==p2Value) {
            int p1High = Math.max(Utility.getRankValue(p1.getHand().get(1).getRank()),Utility.getRankValue(p1.getHand().get(0).getRank()));
            int p2High = Math.max(Utility.getRankValue(p2.getHand().get(1).getRank()),Utility.getRankValue(p2.getHand().get(0).getRank()));
            if (p1High>p2High){
                return "Player 1 wins!";
            }
            else if (p1High<p2High){
                return "Player 2 wins!";
            }
        }
        return "Tie!";
    }

   
    public static void play(){ //simulate card playing
    }
        
        

}