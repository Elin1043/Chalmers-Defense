package com.mygdx.chalmersdefense.Model.CustomExceptions;

/**
 * @author Joel Båtsman Hilmersson
 *
 * Exception to use when there is something wrong with round data
 */
public class IllegalRoundDataException extends RuntimeException{

    public IllegalRoundDataException(String message) {
        super(message);
    }
}
