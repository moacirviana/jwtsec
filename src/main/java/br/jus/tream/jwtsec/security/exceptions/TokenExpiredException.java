package br.jus.tream.jwtsec.security.exceptions;

public class TokenExpiredException extends RuntimeException{
	private static final long serialVersionUID = -4035148962774372101L;

	public TokenExpiredException (String msg) {
		super(msg);
	}
	
	public TokenExpiredException(String msg, Throwable cause) {
		super (msg, cause);
	}

}