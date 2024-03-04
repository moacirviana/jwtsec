package br.jus.tream.jwtsec.controller.exception;

public class BaseException extends RuntimeException{
    private static final long serialVersionUID = 8293699420980326926L;

	private String code;

    private String message;

	public BaseException(String code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}