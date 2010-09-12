/**
 * 
 */
package org.sri.blogsrus.services.mockito;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.sri.blogsrus.data.Comment;
import org.sri.blogsrus.dto.CommentDto;
import org.sri.blogsrus.exceptions.DatabaseConnectionException;
import org.sri.blogsrus.exceptions.WriteException;
import org.sri.blogsrus.services.CommentService;

/**
 * Example of mocking a constructor invocation.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(CommentService.class)
public class MockConstruction {
	private static final String FAKE_ID = "007";
	private static final Long SOME_BLOG_ID = 93L;
	private CommentService commentService;

	@Before public final void setup() throws DatabaseConnectionException {
		Comment mockComment = PowerMockito.mock(Comment.class);
		Mockito.when(mockComment.toDto()).thenReturn(new CommentDto(FAKE_ID));
		commentService = new CommentService();
	}
	
	/**
	 * Watch the construction of a {@link Comment} be intercepted.
	 * @throws WriteException
	 */
	@Test public final void mock_constructor() throws WriteException {
		commentService.submitComment(SOME_BLOG_ID, "Dog days of summer");
		PowerMockito.verifyNew(Comment.class);
	}
}
