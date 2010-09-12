package org.sri.blogsrus.services.mockito;

import org.mockito.ArgumentMatcher;
import org.sri.blogsrus.data.Comment;

/**
 * A comment matcher
 */

class CommentMatcher extends ArgumentMatcher<Comment> {

	@Override
	public boolean matches(Object argument) {
		return argument != null && argument instanceof Comment;
	}
	
}