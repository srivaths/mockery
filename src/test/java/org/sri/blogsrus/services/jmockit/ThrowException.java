/**
 * 
 */
package org.sri.blogsrus.services.jmockit;

import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;

import org.junit.Before;
import org.junit.Test;
import org.sri.blogsrus.dao.CommentDao;
import org.sri.blogsrus.data.Comment;
import org.sri.blogsrus.exceptions.DatabaseConnectionException;
import org.sri.blogsrus.exceptions.WriteException;
import org.sri.blogsrus.services.CommentService;
import org.sri.blogsrus.services.UserInformationService;

/**
 * Tests of {@link CommentService} using JMockit.
 */
public class ThrowException {
	private static final Long MOCK_USER_ID = 17L;
	private static final long SOME_BLOG_ID = 332L;
	@Mocked private UserInformationService mockUserInformationService;
	@Mocked private CommentDao mockCommentDao;
	private CommentService commentService;
	
	@Before
	public void setup() throws DatabaseConnectionException {
		commentService = new CommentService();
		commentService.setUserInformationService(mockUserInformationService);
	}

	@Test
	public final void throw_an_exception() throws WriteException {
		new Expectations() {
			{
				mockCommentDao.insertComment((Comment)any); 
				result = new WriteException();
			}
		};
		
		commentService.setUserInformationService(mockUserInformationService);
		commentService.submitComment(SOME_BLOG_ID, 
				"One flew over the cuckoo's nest.");
		
		new Verifications() {
			{
				mockUserInformationService.getCurrentUserId();
			}
		};
	}
}
