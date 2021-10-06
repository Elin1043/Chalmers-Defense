package com.mygdx.chalmersdefense.model.customExceptions;

/**
 * @author Joel Båtsman Hilmersson
 *
 * Exeption to call if round data is not valid
 */
public class IllegalRoundDataException extends RuntimeException{

    public IllegalRoundDataException(String message) {
        super(message);
    }
}
