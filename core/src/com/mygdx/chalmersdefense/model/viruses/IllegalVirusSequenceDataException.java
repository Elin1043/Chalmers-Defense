package com.mygdx.chalmersdefense.model.viruses;

/**
 * @author Joel Båtsman Hilmersson
 * <p>
 * Exeption to call if round data is not valid
 */
final public class IllegalVirusSequenceDataException extends RuntimeException {

    /**
     * Creates this exception
     * @param message the message this exception should display
     */
    public IllegalVirusSequenceDataException(String message) {
        super(message);
    }
}
