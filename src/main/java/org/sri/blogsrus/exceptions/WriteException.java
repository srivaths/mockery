package org.sri.blogsrus.exceptions;

public class WriteException extends Exception {
	private static final long serialVersionUID = 1L;

	public WriteException() {
		
	}
	public WriteException(String message) {
		super(message);
	}
}
