package com.mygdx.chalmersdefense.model.viruses;

/**
 * @author Joel Båtsman Hilmersson
 * <p>
 * Exeption to call if round data is not valid
 */
public class IllegalRoundDataException extends RuntimeException {

    public IllegalRoundDataException(String message) {
        super(message);
    }
}
