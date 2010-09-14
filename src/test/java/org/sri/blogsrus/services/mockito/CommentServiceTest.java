package org.sri.blogsrus.services.mockito;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.sri.blogsrus.dao.CommentDao;
import org.sri.blogsrus.data.Comment;
import org.sri.blogsrus.exceptions.WriteException;
import org.sri.blogsrus.services.AuditService;
import org.sri.blogsrus.services.CommentService;
import org.sri.blogsrus.services.UserInformationService;
import org.sri.blogsrus.constants.Operation;

/**
 * Tests of {@link CommentService} methods.
 */

@RunWith(MockitoJUnitRunner.class)

public class CommentServiceTest {

	private static final long SOME_BLOG_ID = 332L;
	private static final Long MOCK_USER_ID = 17L;
	@Mock private UserInformationService mockUserInformationService;
	@Mock private CommentDao mockCommentDao;
	@Mock private AuditService auditService;
	private CommentService commentService;

	@Before
	public void setup() throws Exception {
		commentService = new CommentService();
		commentService.setAuditService(auditService);
		when(mockUserInformationService.getCurrentUserId())
			.thenReturn(MOCK_USER_ID);
		commentService.setUserInformationService(mockUserInformationService);
	}

	/**
	 * Test method for
	 * {@link org.sri.mockery.services.CommentService#submitComment(java.lang.Long, java.lang.String)}
	 * .
	 * @throws WriteException 
	 */
	
	@Test
	public final void submitComment_happy_day() throws WriteException {

		commentService.submitComment(SOME_BLOG_ID, 
				"One flew over the cuckoo's nest.");

		verify(mockUserInformationService).getCurrentUserId();
	}

	@Test
	public final void submitComment_exception_on_insert() 
									throws WriteException {
		
		when(
				mockCommentDao.insertComment(any(Comment.class))
			)
			.thenThrow(new WriteException());
		
		commentService.setCommentDao(mockCommentDao);
		
		commentService.submitComment(SOME_BLOG_ID, 
				"Yet another comment.");
		
		verify(auditService,never())
			.audit(any(Operation.class), any(Long.class));
		
	}

}
