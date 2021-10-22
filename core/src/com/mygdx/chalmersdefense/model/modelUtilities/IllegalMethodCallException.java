package com.mygdx.chalmersdefense.model.modelUtilities;

/**
 * @author Joel Båtsman Hilmersson
 * <p>
 * Exeption to call if a method should not be called
 */
final class IllegalMethodCallException extends RuntimeException {
    public IllegalMethodCallException(String message) {
        super(message);
    }
}
