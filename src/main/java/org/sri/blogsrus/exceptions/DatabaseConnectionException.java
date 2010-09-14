/* $Id$ */
package org.sri.blogsrus.exceptions;

/**
 * Exception indicating an error with the database connection.
 */
public class DatabaseConnectionException extends Exception {
	private static final long serialVersionUID = 1L;

	public DatabaseConnectionException(String message) {
		super(message);
	}
}
