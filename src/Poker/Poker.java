/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Poker;

import java.util.InputMismatchException;
import java.util.Scanner;
import static Poker.InvalidInputException.checkInputError;

/**
 * @author Nolan Rink nvrk4b
 */

public class Poker {

    
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        
        
        //ask if user wants to play
        boolean again = true;
        int play = -1;
        while(again)
        {
            try
            {
                System.out.println("Do you want to play Texas Hold'Em (type '1' to confirm or '2' to exit)");
                play = scan.nextInt();
                checkInputError(play,1,2);
                again = false;
            }
            catch(InvalidInputException | InputMismatchException ex)
            {
                System.out.println("Invalid input:");
                System.out.println("Enter anything to try again");
                scan.next();
            }
        }
        
        
        //Setup for the actual game
        if(play == 1)
        {
            int playerAmount = 0;
            again = true;
            while(again)
            {
                try
                {
                    System.out.println("How many players are playing? (enter a number 2-10)");
                    playerAmount = scan.nextInt();
                    checkInputError(playerAmount,2,3,4,5,6,7,8,9,10);
                    again = false;
                }
                catch(InvalidInputException | InputMismatchException ex)
                {
                    System.out.println("Invalid input:");
                    System.out.println("Enter anything to try again");
                    scan.next();
                }
            }
            PokerPlayer[] players = new PokerPlayer[playerAmount];
       
            for(int i = 0; i < playerAmount; i++)
            {
                int playerNum = 1;
                String name = null;
                int stackSize = 0;
                again = true;
                while(again)
                {
                    try
                    {
                        System.out.println("enter player " + i + "'s name");
                        name = scan.next();
                        again = false;
                    }
                    catch(InputMismatchException ex)
                    {
                        System.out.println("Invalid input:");
                        System.out.println("Enter anything to try again");
                        scan.next();
                    }
                }
                again = true;
                while(again)
                {    
                    try
                    {
                        System.out.println("enter " + name + "'s stack size (number between 1 - 1000000 with no commas in between)");
                        stackSize = scan.nextInt();
                        if(stackSize <= 0 || stackSize >= 1000000)
                        {
                            checkInputError(stackSize,1);  //throw error if negative stack 
                        }
                        again = false;
                    }
                    catch(InvalidInputException | InputMismatchException ex)
                    {
                        System.out.println("Invalid input:");
                        System.out.println("Enter anything to try again");
                        scan.next();
                    }                    
                    players[i] = new PokerPlayer(name, stackSize);
                    playerNum++;
                }
            }           
            
            
            //creates a poker game with the array created before
            PokerTexasHoldEm game  = new PokerTexasHoldEm(players);

            //set blinds/ante   
            game.setup(scan);
            
            //loop to play hands consecutively
            boolean playAgain = true;
            again = true;
            while(playAgain)
            {
                game.dealCards();
                
                PokerPlayer hold = game.getPlayer(game.getPlayerAction());
                PokerPlayer holdNext = game.getPlayer(game.noIncremenentGetNextPlayerAction());
                
                System.out.println("\nPre-flop betting: " + hold
                        + " is the small blind and " + holdNext + " is the big blind\n");
                
                //pre-flop betting
                game.roundOfBetting(hold, scan);
               
                game.flop(); //show the flop cards
                //post-flop betting
                game.roundOfBetting(hold, scan);
                                
                game.turn(); //show the turn card
                //Turn betting
                game.roundOfBetting(hold, scan);
                
                game.river(); //show the river card
                //final betting
                game.roundOfBetting(hold, scan);
                
                
                game.DeclareHandWinner(scan);
                game.printStandings();
                
                while(again)
                {
                    try
                    {
                        System.out.println("Enter 1 to play again or 2 to stop");
                        play = scan.nextInt();
                        checkInputError(play,1,2);
                        if(play == 2) playAgain = false;
  
                        again = false;
                    }
                    catch(InvalidInputException | InputMismatchException ex)
                    {
                        System.out.println("Invalid input:");
                        System.out.println("Enter anything to try again");
                        scan.next();
                    }
                }
            }
        }
        
        scan.close();        
    }
    
}
