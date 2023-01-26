/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Poker;

//
import static Poker.InvalidInputException.checkInputError;
import java.util.List;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author nolan
 */
public class PokerTexasHoldEm implements IPoker {
    
    private Card[] tableCards = new Card[5];
    private List<PokerPlayer> players;
    private CardDeck deck = new CardDeck();
    private int dealer = 0, playerUp = 0, lastBet = 0, playersInHand = 0,
            pot = 0, bigBlind , littleBlind, ante;
    private boolean hold = true;
    
    PokerTexasHoldEm(PokerPlayer[] p)
    {
        players = Arrays.asList(p);
    }
    
    @Override
    public void setLittleBlind(int LB)
    {
        this.littleBlind = LB;
    }
    
    @Override
    public void setBigBlind(int BB)
    {
        this.bigBlind = BB;
    }

    @Override
    public void setAnte(int ante)
    {
        this.ante = ante;
    }

    
    @Override
    public void dealCards()
    {
        deck.shuffle();
        
        setPot(0);
        
        playersInHand = 0;
        for(PokerPlayer x : players) //put players in hand if they have chips
        {
            x.resetChipsInPot();
            if(x.getChips() > 0)
            {
                x.setInHand(true);
            }
        }
            playersInHand = getPlayersInHand();
        
        for(PokerPlayer player : players)
        {
            player.setCards(deck.deal(), deck.deal());            
        }
        
        for(int i = 0; i < 5; i++)
        {
            tableCards[i] = deck.deal();
        }
        
        playerUp = getNextPlayerAction();        
        
        while(!players.get(dealer+1).getInHand())
        {
            dealer++;
        }
    }
    
    public PokerPlayer getPlayer(int index)
    {
        return players.get(index);
    }
    
    //returns the index of the player whose action is next
    public int getNextPlayerAction()
    {
        if(players.size() == (playerUp+1))
        {
            playerUp = 0;
            
        }
        else
        {
            playerUp++;
        }
        
        while(!players.get(playerUp).getInHand())
        {
            if(players.size() == (playerUp+1))
            {
                playerUp = 0;
            
            }
            else
            {
                playerUp++;
            }
            
        }
        
        return playerUp;
    }
    
    public int noIncremenentGetNextPlayerAction()
    {
        int playerUpHold = playerUp;
        if(players.size() == (playerUpHold+1))
        {
            playerUpHold = 0;
            
        }
        else
        {
            playerUpHold++;
        }
        
        while(!players.get(playerUpHold).getInHand())
        {
            if(players.size() == (playerUpHold+1))
            {
                playerUpHold = 0;
            
            }
            else
            {
                playerUpHold++;
            }
            
        }
        
        return playerUpHold;
    }
    
    //returns the index of the player whose action it is on
    public int getPlayerAction()
    {
        return playerUp;
    }
    
    //returns the number of players in the hand
    public int getPlayersInHand()
    {
        playersInHand = 0;
        for(PokerPlayer x : players)
        {
            if(x.getInHand())
            {
                playersInHand++;
            }
        }
        return playersInHand;
    }
    
    public void setPot(int potTotal)
    {
        pot = potTotal;
    }

    public void bet(int bet)
    {
        int chips = players.get(playerUp).getChips();
        int total = chips - bet;
        players.get(playerUp).setChips(total);
        lastBet = bet;
        players.get(playerUp).addChipsInPot(bet);
        pot += bet;
        
    }
    
    private int largestAmountInPot()
    {
        int hold = 0;
        for(PokerPlayer x : players)
        {
            if(x.getChipsInPot() > hold)
            {
                hold = x.getChipsInPot();
            }
        }
        return hold;
    }
    
    public void call()
    {
        if(players.get(playerUp).getChips() < largestAmountInPot())
        {
            bet(players.get(playerUp).getChips());            
        }
        else
        {
            bet((largestAmountInPot() - players.get(playerUp).getChipsInPot()));            
        }
        
    }
    
    public void check()
    {
        bet(0);
    }
    
    
    public void fold()
    {
        players.get(playerUp).setInHand(false);
    }    
    
    @Override
    public void payOut(PokerPlayer... x)
    {
        int payOut = pot / x.length;
        for(PokerPlayer winner : x)
        {
            int chips = winner.getChips() + payOut;
            winner.setChips(chips);
        }
    }
    
    private boolean playerBetsEqual()
    {
        int biggestBet = 0;
        for(PokerPlayer x : players)
        {
            if(x.getInHand() && x.getChipsInPot() >= biggestBet)
            {
                biggestBet = x.getChipsInPot();
            }
        }
        
        for(PokerPlayer x : players)
        {
            if(x.getInHand() && x.getChipsInPot() != biggestBet && x.getChips() > 0)
            {
                return false;
            }
        }
        return true;    
    }
    
    public void DeclareHandWinner(Scanner scan)
    {
        int winnerIndex;
        boolean again;
        again = true;
        while(again)
        {
            try
            {
                System.out.println("\nEnter the seat number of the winner");
                System.out.println("Here are the players and seat numbers:\n");
                for(int i = 0; i < players.size(); i++)
                {
                    System.out.println("Seat #" + i + " " + players.get(i) 
                            + " {" + players.get(i).getCardOne() + ", " + players.get(i).getCardTwo() + "}");
                }
                System.out.println("The board was (" + tableCards[0] + ", " + tableCards[1] + ", " 
                + tableCards[2] + ", " + tableCards[3] + ", " + tableCards[4] + ")");
                winnerIndex = scan.nextInt();
                if(winnerIndex < 0 || winnerIndex >= players.size())
                {
                    checkInputError(0,1);   
                }
                System.out.println(players.get(winnerIndex).getFirstName() + " won!!!");
                payOut(players.get(winnerIndex));
                again = false;
            }
            catch(InvalidInputException | InputMismatchException ex)
            {
                System.out.println("Invalid input:");
                System.out.println("Enter anything to try again");
                scan.next();
                continue;
            }
        }
    }     

      
    public void printCards(PokerPlayer p)
    {
        System.out.println(p.toString() + " (" + p.getCardOne() + ", " + p.getCardTwo() + ")");
    }
    
    
    public void flop()
    {
        System.out.println("\n*******The flop is (" + tableCards[0] + " ," + tableCards[1] + " ," + tableCards[2] + ")*******");
    }
    
    
    public void turn()
    {
        System.out.println("\nThe turn is the " + tableCards[3]);
        System.out.println("*******The board now shows (" + tableCards[0] + " ," + tableCards[1] + " ," + tableCards[2] + " ," + tableCards[3] + ")*******");
    }
    
    
    public void river()
    {
        System.out.println("\nThe river is the " + tableCards[4]);
        System.out.println("*******The board now shows (" + tableCards[0] + " ," + tableCards[1] + " ," 
                + tableCards[2] + " ," + tableCards[3] + " ," + tableCards[4] + ")*******");
    }
    

    public void printPlayerNumbers()
    {
        for(int i = 0; i < players.size(); i++)
        {
            System.out.println("player #" + i + "is " + players.get(i));
        }
    }
    
    public void printStandings()
    {
        for(PokerPlayer x : players)
        {
            System.out.println(x);
        }
    }
    
    public void setup(Scanner scan)
    {
            int sb, bb, ante;
            boolean again;
            again = true;
            while(again)
            {
                try
                {
                    System.out.println("What is the ante? (enter a posotive non-decimal number)");
                    ante = scan.nextInt();
                    if(ante < 0)
                    {
                        checkInputError(ante,1);  //throw error if negative ante 
                    }
                    setAnte(ante);
                    again = false;
                }
                catch(InvalidInputException | InputMismatchException ex)
                {
                    System.out.println("Invalid input:");
                    System.out.println("Enter anything to try again");
                    scan.next();
                    continue;
                }
                
                try
                {
                    System.out.println("What is the small blind? (enter a posotive non-decimal number)");
                    sb = scan.nextInt();
                    if(sb < 0)
                    {
                        checkInputError(sb,1);  //throw error if negative blind 
                    }
                    setLittleBlind(sb);
                    again = false;
                }
                catch(InvalidInputException | InputMismatchException ex)
                {
                    System.out.println("Invalid input:");
                    System.out.println("Enter anything to try again");
                    scan.next();
                    continue;
                }
                
                try
                {
                    System.out.println("What is the big blind? (enter a posotive non-decimal number)");
                    bb = scan.nextInt();
                    if(bb < 0)
                    {
                        checkInputError(bb,1);  //throw error if negative blind 
                    }
                    setBigBlind(bb);
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
    
    public void roundOfBetting(PokerPlayer hold, Scanner scan) throws InvalidInputException
    {
        boolean equal = true;
        while(equal)
        {
            for(int i = 0; i < getPlayersInHand(); i++)
            {
                boolean again = true;
                int actionType = 0;
                while(again)
                {
                    try
                    {
                        System.out.println("\nThere are " + getPlayersInHand() + " players in the hand.");                            
                        System.out.println(hold + " action is on you. Your cards are:\n{" +
                                hold.getCardOne() + ", " + hold.getCardTwo() + "}");
                        System.out.println("\nYou have " + hold.getChipsInPot() + " chips in the pot" + "(press 1 to bet, 2 to fold, "
                                + "3 to call, or 4 to check)");
                        actionType = scan.nextInt();
                        checkInputError(actionType,1,2,3,4);
                        again = false;
                    }
                    catch(InvalidInputException | InputMismatchException ex)
                    {
                        System.out.println("Invalid input:");
                        System.out.println("Enter anything to try again");
                        scan.next();
                    }
                }


                again = true;
                int bet = 0;
                if(actionType == 1)
                {
                    while(again)
                    {
                        try
                        {
                            System.out.println(hold + " how much do you want to bet? "
                                    + "(enter a posotive whole number <= your chip count)");
                            bet = scan.nextInt();
                            if(bet <= 0 || hold.getChips() < bet)
                            {
                                checkInputError(0,1);  //throw error
                            }
                            again = false;
                        }
                        catch(InvalidInputException | InputMismatchException ex)
                        {
                            System.out.println("Invalid input:");
                            System.out.println("Enter anything to try again");
                            scan.next();
                        }
                    }
                    bet(bet);
                }

                if(actionType == 2)
                {
                    fold();
                }

                if(actionType == 3)
                {
                    call();
                }
                
                if(actionType == 4)
                {
                    check();
                }

                hold = getPlayer(getNextPlayerAction());
            }
            if(playerBetsEqual())
            {
                equal = false;
            }
        }    
    }
    
}
