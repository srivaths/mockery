package org.sri.blogsrus.services.easymock;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import org.junit.Before;
import org.junit.Test;
import org.sri.blogsrus.dao.CommentDao;
import org.sri.blogsrus.data.Comment;
import org.sri.blogsrus.exceptions.WriteException;
import org.sri.blogsrus.services.CommentService;
import org.sri.blogsrus.services.UserInformationService;

/**
 * Tests of {@link CommentService} methods.
 */
public class CommentServiceTest {

	private static final long SOME_BLOG_ID = 332L;
	private static final Long MOCK_USER_ID = 17L;
	private UserInformationService mockUserInformationService;
	private CommentDao mockCommentDao;
	private CommentService commentService;

	@Before
	public void setup() throws Exception {
		commentService = new CommentService();
		mockUserInformationService = createMock(UserInformationService.class);
		expect(mockUserInformationService.getCurrentUserId()).
		andReturn(MOCK_USER_ID);
		commentService.setUserInformationService(mockUserInformationService);

		mockCommentDao = createMock(CommentDao.class);
		commentService.setCommentDao(mockCommentDao);
	}

	/**
	 * Test method for
	 * {@link org.sri.mockery.services.CommentService#submitComment(java.lang.Long, java.lang.String)}
	 * .
	 * @throws WriteException 
	 */
	@Test
	public final void submitComment_happy_day() throws WriteException {
		replay(mockUserInformationService);
		commentService.submitComment(SOME_BLOG_ID, 
				"One flew over the cuckoo's nest.");

		verify(mockUserInformationService);
	}

	@Test
	public final void submitComment_exception_on_insert() 
										throws WriteException {
		
		
		expect(
				mockCommentDao.insertComment(anyObject(Comment.class))
			  )
			  .andThrow(new WriteException(""));
		
		replay(mockUserInformationService);
		commentService.submitComment(SOME_BLOG_ID, 
									"Yet another comment.");

	}

}
