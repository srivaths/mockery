/**
 * 
 */
package org.sri.blogsrus.util;

import java.util.Collection;

import org.apache.log4j.Logger;

/**
 * Utility methods.
 */
public class Utility {
	private static final Logger logger = Logger.getLogger(Utility.class);
	public static final String PERIOD = ".";
	
	/**
	 * Identifies the given string as empty or not.
	 * @param input The string to test.
	 * @return <tt>true</tt> if empty and <tt>false</tt> otherwise.
	 */
	public static final boolean isEmpty(String input) {
		logger.trace("Testing '" + input + "' for emptiness.");
		return input == null || input.trim().length() == 0;
	}
	
	/**
	 * Identifies the given collection as empty or not
	 * @param input The {@link Collection} to test.
	 * @return <tt>true</tt> if empty and <tt>false</tt> otherwise.
	 */
	public static final boolean isEmpty(Collection<?> input) {
		logger.trace("Testing collection for emptiness.");
		return input == null || input.size() == 0;
	}
	
	/**
	 * Converts the given text into sentences -- capitalizing the first letter of each sentence.
	 * @param input The text to process.
	 * @return The given text converted into sentences.
	 */
	public static final String toSentences(String input) {
		logger.trace("Converting (" + input + ") into sentence(s).");
		if(isEmpty(input)) {
			logger.info("Cannot convert an empty string into a sentence.");
			return input;
		}
		String[] tokens = input.split("\\.");
		
		StringBuilder builder = new StringBuilder(); 
		for(String token: tokens) {
			/*
			 * Parse token:
			 *   First letter capitalized, rest not.
			 */
	        String firstLetter = token.substring(0,1);
	        String remainder   = token.substring(1);
	        builder.append(firstLetter.toUpperCase() + remainder.toLowerCase());
	        
	        /*
	         * Terminate the sentence
	         */
	        builder.append(PERIOD);
		}
		logger.trace("Sentence form is (" + builder.toString() + ").");
		return builder.toString();
	}
}