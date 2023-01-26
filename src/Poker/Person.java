/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Poker;

/**
 *
 * @author nolan
 */
public class Person {
    
    private String firstName, lastName;
    
    //constructor
    Person(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    Person(String firstName)
    {
        this.firstName = firstName;
    }
    
    //return the first and last name
    public String getName()
    {
        return this.firstName + " " + this.lastName;
    }
        
    //returns the the first name
    public String getFirstName()
    {
        return this.firstName;
    }
        
    //returns the the last name
    public String getLastName()
    {
        return this.lastName;
    }
    
    //changes the first and last name of an person thats already been constructed 
    public void setName(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    } 
    
    //overrides the default toString
    @Override
    public String toString()
    {
        return this.firstName + " " + this.lastName;                            
    }
    
    
}
