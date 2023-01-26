/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Poker;

/**
 *
 * @author nolan
 */
public class InvalidInputException extends RuntimeException {
    
    public InvalidInputException(int input)
    {
        super("The input " + input + " is not valid");
    }
    
    public InvalidInputException(String input)
    {
        super("The input " + input + " is not valid");
    }
    
    public static void checkInputError(int input, int...valid) throws InvalidInputException
    {
        boolean holdIsError = true;
        for(int x : valid)
        {
            if(x == input) holdIsError = false;          
        }
        if(holdIsError) throw new InvalidInputException(input);
    }
}
