/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Poker;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
        

/**
 *
 * @author nolan
 */
public class CardDeck {
    
    private List<Card> deck;
    private int iterator = 0;
    
    //constructor
    public CardDeck()
    {
        Card[] cards = new Card[52];
        int cardCount = 0;
        
        for(Card.Suit suit: Card.Suit.values())
        {
            for(Card.Face face : Card.Face.values())
            {
                cards[cardCount] = new Card(face, suit);
                cardCount++;
            }            
        }
        deck = Arrays.asList(cards);
    }
    
    //shuffle the deck of cards
    public void shuffle()
    {
        Collections.shuffle(deck);
    }
    
    //print the deck of cards
    public void printCards()
    {
        for(int i = 0; i < deck.size(); i++)
        {
            System.out.printf("%-19s%s", deck.get(i), ((i+1) % 4 == 0) ? "\n" : "");
        }
    }
    
    public Card deal()
    {
        if(iterator >= 52)
        {
            iterator = 0;
        }
        
        Card x = deck.get(iterator);
        iterator++;
        return x;
    }
    
    
}
