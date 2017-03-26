package com.quakearts.security.cryptography;

public class IllegalCryptoActionException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4173764902983590551L;

	public IllegalCryptoActionException() {
        super();
    }

   public IllegalCryptoActionException(String message) {
        super(message);
    }
    
    public IllegalCryptoActionException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalCryptoActionException(Throwable cause) {
        super(cause);
    }
}
