/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Poker;

/**
 *
 * @author nolan
 */
public class PokerPlayer extends Person {
   
    public static Card[] tableCards;
    private Card[] cards = new Card[2];
    private int chips;
    private boolean inHand = true;
    private int chipsInPot = 0;
    
    PokerPlayer(String firstName, int chips)
    {
        super(firstName);
        this.chips = chips;
        cards[0] = null;
        cards[1] = null;
    }
    

    //setters
    public void setCards(Card c1, Card c2)
    {
        cards[0] = c1;
        cards[1] = c2;
    }
    
    public void setChips(int num)
    {
        chips = num;
    }
    
    public void setInHand(boolean x)
    {
        inHand = x;
    }    
    
    public void resetChipsInPot()
    {
        chipsInPot = 0;
    }
    
    public void addChipsInPot(int chips)
    {
        chipsInPot += chips;
    }
            
    
    //getters
    public int getChips()
    {
        return this.chips;
    }
    
    public Card getCardOne()
    {
        return cards[0];
    }
    
    public Card getCardTwo()
    {
        return cards[1];
    }
    
    public int getChipsInPot()
    {
        return chipsInPot;
    }

    public void printHand()
    {
        System.out.println("Your hand is (" + getCardOne() + ", " + getCardTwo() + ")");
    }
    
    public boolean getInHand()
    {
        return inHand;
    }
    
    
    //overrides the default toString
    @Override
    public String toString()
    {
        return getFirstName() + "(" + getChips() + " chips)";
    }
}
