package com.example.project;
import java.util.ArrayList;

public class Player{
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    public Player(){
        hand = new ArrayList<>();
        allCards = new ArrayList<>(); //initialize allCards
    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    public void addCard(Card c){
        hand.add(c); //adds a new card to hand
    }


    public boolean checkFlush() {
        ArrayList<Integer> checker = findSuitFrequency();
        for (int i : checker) { //Check for flush if every card is the same rank
            if (i==5) {return true;} //If there is only one suit then early return true
        }
        return false; //return false if condition is not met
    }


    public int checkNumberOfPair() {
        int num=0; //num represents the number of pairs
        for (int i : findRankingFrequency()) {
            if (i==2) {num++;} //Add to num if there is two cards of same rank
        }
        return num;
    }


    public boolean checkThreeOfAKind() {
        for (int i : findRankingFrequency()) {
            if (i==3) {return true;} //Checking if there are any ranks with 3 cards
        } //early return true if three of a kind is found
        return false; //return false if condition is not met
    }


    public boolean checkStraight() {
        sortAllCards(); //Make sure the cards are sorted

        int consecutive = 1; // Track consecutive cards
        for (int i = 0; i < allCards.size() - 1; i++) {
            int currentRank = Utility.getRankValue(allCards.get(i).getRank());
            int nextRank = Utility.getRankValue(allCards.get(i + 1).getRank());
            if (nextRank == currentRank + 1) { 
                consecutive++; // Found consecutive rank
            } else if (nextRank != currentRank) { 
                consecutive = 1; // Reset if not consecutive (ignoring duplicates)
            }
            if (consecutive == 5) { // A straight needs at least 5 consecutive cards
                return true; //Early return true if straight
            }
        }
        return false;
    }
    
    public boolean checkFourKind() {
        for (int i : findRankingFrequency()) {
            if (i==4) {return true;} //Checking if there are any ranks with 4 cards
        }
        return false;
    }

    public boolean checkRoyal() {
        //Check to see if every royal card is present at least once
        if (findRankingFrequency().get(12)==1 && findRankingFrequency().get(11)==1 && findRankingFrequency().get(10)==1 && findRankingFrequency().get(9)==1 && findRankingFrequency().get(8)==1) {
            return true;
        }
        return false;
    }

    public String playHand(ArrayList<Card> communityCards){
        //Set up allCards by combining hand and community cards
        allCards.clear();
        allCards.addAll(hand);
        allCards.addAll(communityCards);
        sortAllCards();

        //set variables used to check if the conditions of the hands are met
        boolean flush = checkFlush();
        int numPairs = checkNumberOfPair();
        boolean checkForThree = checkThreeOfAKind();
        boolean straight = checkStraight();
        boolean checkFour = checkFourKind();
        boolean royal = checkRoyal();

        //Start from the best hand and go down until the conditions match
        if (royal==true && flush==true) {
            return "Royal Flush";
        } else if (flush==true && straight==true) {
            return "Straight Flush";
        } else if(checkFour==true) {    
            return "Four of a Kind";
        } else if(checkForThree==true && numPairs==1) {
            return "Full House";
        } else if(flush==true) {
            return "Flush";
        } else if(straight==true) {
            return "Straight";
        } else if(checkForThree==true) {
            return "Three of a Kind";
        } else if(numPairs==2) {
            return "Two Pair";
        } else if(numPairs==1) {
            return "A Pair";
        } else if (hand.contains(allCards.get(allCards.size()-1))) { //Since allCards is sorted the high card will be the last one; this checks if hand contains that card
            return "High Card";
        }
        return "Nothing";
    }




public void sortAllCards() {
    if (allCards == null || allCards.size() <= 1) {
        return; // Nothing to sort if the list is null or has 0-1 element
    }
    for (int i = 1; i < allCards.size(); i++) {
        Card cur = allCards.get(i);
        int curValue = Utility.getRankValue(cur.getRank());
        int j = i;
        while (j > 0 && curValue < Utility.getRankValue(allCards.get(j - 1).getRank())) {
            allCards.set(j, allCards.get(j - 1));
            j--;
        }
        allCards.set(j, cur);
    }
}

    public ArrayList<Integer> findRankingFrequency(){
        ArrayList<Integer> rankingFrequency = new ArrayList<Integer>(); //make a new arrayList
        for (int i=0; i<ranks.length; i++) { //go throught all the ranks
            int count=0;
            for (Card c : allCards) { //check every card 
                if (Utility.getRankValue(c.getRank())==Utility.getRankValue(ranks[i])) {count++;} //add to count if the card has the same rank as the current iteration of ranks
            }
            rankingFrequency.add(count); //add the count to the new arrayList
        }
        return rankingFrequency;
    }

    public ArrayList<Integer> findSuitFrequency(){
        ArrayList<Integer> suitFrequency = new ArrayList<Integer>(); //make a new arrayList
        for (int i=0; i<suits.length; i++) { //go through all the suits
            int count=0;
            for (Card c : allCards) { //check every card 
                if (c.getSuit().equals(suits[i])) {count++;} //add to count if the card has the same suit as the current iteration of suits
            }
            suitFrequency.add(count); //add the count to the new arrayList
        }
        return suitFrequency; 
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }




}
