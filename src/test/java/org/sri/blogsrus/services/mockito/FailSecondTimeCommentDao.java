package org.sri.blogsrus.services.mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.sri.blogsrus.dao.CommentDao;
import org.sri.blogsrus.data.Comment;
import org.sri.blogsrus.exceptions.WriteException;
import org.sri.blogsrus.services.CommentService;
import org.sri.blogsrus.services.UserInformationService;
import org.sri.blogsrus.exceptions.*;

/**
 * Tests of {@link CommentService} methods.
 */

@RunWith(MockitoJUnitRunner.class)

public class FailSecondTimeCommentDao {

	private static final long SOME_BLOG_ID = 332L;
	private static final String SOME_TEXT = "e = mc*c";
	private static final Long MOCK_USER_ID = 17L;
	@Mock private UserInformationService mockUserInformationService;
	@Mock private CommentDao mockCommentDao;
	private CommentService commentService;

	@Before
	public void setup() throws Exception {
		commentService = new CommentService();
	}

	/**
	 * Test method for
	 * {@link org.sri.mockery.services.CommentService#submitComment(java.lang.Long, java.lang.String)}
	 * .
	 * @throws WriteException 
	 */
	
	@Test
	public final void fail_first_then_succeed() throws Exception {
		CommentDao spy = PowerMockito.spy(new CommentDao());
		PowerMockito.when(spy, "establishConnection")
			
			.thenThrow(new DatabaseConnectionException("Rats!"))
			
			.thenCallRealMethod();
		
		commentService.setCommentDao(spy);
		commentService.submitComment(SOME_BLOG_ID, SOME_TEXT);

		verify(mockUserInformationService).getCurrentUserId();
	}

	@Test
	public final void submitComment_exception_on_insert() 
									throws WriteException {
		
		doThrow(new WriteException())
				.when(mockCommentDao).insertComment(any(Comment.class));
		
		commentService.setCommentDao(mockCommentDao);
		
		commentService.submitComment(SOME_BLOG_ID, 
				"Yet another comment.");

	}

}
