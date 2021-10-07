package com.mygdx.chalmersdefense.model.viruses;

/**
 * @author Joel Båtsman Hilmersson
 * <p>
 * Exeption to call if round data is not valid
 */
public class IllegalVirusSequenceDataException extends RuntimeException {

    public IllegalVirusSequenceDataException(String message) {
        super(message);
    }
}
