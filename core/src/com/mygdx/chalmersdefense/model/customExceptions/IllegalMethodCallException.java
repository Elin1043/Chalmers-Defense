package com.mygdx.chalmersdefense.model.customExceptions;

/**
 * @author Joel Båtsman Hilmersson
 *
 * Exeption to call if a method should not be called
 */
public class IllegalMethodCallException extends RuntimeException{
    public IllegalMethodCallException(String message) {
        super(message);
    }
}
