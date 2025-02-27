package com.example.project;
import java.util.ArrayList;



public class Player{
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    public Player(){
        hand = new ArrayList<>();
    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    public void addCard(Card c){
        hand.add(c); //adds a new card to hand
    }

    public String playHand(ArrayList<Card> communityCards){      
        return "Nothing";
    }

    public void sortAllCards(){ //using insertion sort to sort the cards
        for (int i=1; i<allCards.size(); i++) {
            Card cur = allCards.get(i);
            int z = i;
            while (z>0 && Integer.parseInt(cur.getRank()) < Integer.parseInt((allCards.get(z-1).getRank()))) {
                allCards.set(z, allCards.get(z-1));
                z--;
            }
            allCards.set(z, cur);
        }
    } 

    public ArrayList<Integer> findRankingFrequency(){
        return new ArrayList<>(); 
    }

    public ArrayList<Integer> findSuitFrequency(){
        return new ArrayList<>(); 
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }




}
