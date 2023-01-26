/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Poker;

/**
 *
 * @author nolan
 */

//interface for diffferent types of poker games
public interface IPoker {

    void dealCards();
    void setLittleBlind(int LB);
    void setBigBlind(int BB);
    void setAnte(int ante);
    void payOut(PokerPlayer... x);

    
}
