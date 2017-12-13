package com.kovetstech.trivia;

/**
 * Created by Shay on 12/9/2017.
 */

public class Tools {

    public String DigitTOText(int digit){
        switch(digit){
            case 1: return "One";
            case 2: return "Two";
            case 3: return "Three";
            case 4: return "Four";
            case 5: return "Five";
            case 6: return "Six";
            case 7: return "Seven";
            case 8: return "Eight";
            case 9: return "Nine";
            case 10: return "Ten";
        }
        return null;
    }
    public String Combine(String one, String two){
        return one+two;
    }
}
