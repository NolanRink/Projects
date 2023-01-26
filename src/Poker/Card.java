/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Poker;

/**
 *
 * @author nolan
 */
public class Card {
    //Suits
    public static enum Suit {Diamonds, Hearts, Clubs, Spades}
    
    //Faces
    public enum Face 
    {
        Ace(1), Deuce(2), Three(3), Four(4), Five(5), Six(6), Seven(7), Eight(8),
        Nine(9), Ten(10), Jack(11), Queen(12), King(13);
    
        private final int value;
        
        private Face(int value)
        { 
           this.value = value;
        }
        
        public int getValue()
        {
            return value;
        }
    }
    
    private final Suit suit;
    private final Face face;
    
    //Constructor
    Card(Face face, Suit suit)
    {
        this.face = face;
        this.suit = suit;
    }
    
    //returns Face
    public Face getFace()
    {
        return this.face;
    }
    
    //return Suit
    public Suit getSuit()
    {
        return this.suit;
    }
    
    //overrides the default toString
    @Override
    public String toString()
    {
        return getFace() + " of " + getSuit();                           
    }
    
}
