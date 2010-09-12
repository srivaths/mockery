/**
 * 
 */
package org.sri.blogsrus.services.jmockit;

import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.Verifications;

import org.junit.Test;
import org.sri.blogsrus.dao.CommentDao;
import org.sri.blogsrus.data.Comment;
import org.sri.blogsrus.exceptions.WriteException;
import org.sri.blogsrus.services.CommentService;
import org.sri.blogsrus.services.UserInformationService;

/**
 * Tests of {@link CommentService} using JMockit.
 */


public class FlexibleExpectations {
	private static final Long MOCK_USER_ID = 17L;
	private static final long SOME_BLOG_ID = 332L;
	@Mocked private CommentDao mockCommentDao;
	@Mocked private UserInformationService mockUis;
	private CommentService commentService;

	@Test
	public final void optionally() throws WriteException {
		new NonStrictExpectations() {
			{
				mockUis.getCurrentUserId(); result = MOCK_USER_ID;
				mockCommentDao.insertComment(withAny(new Comment())); result = null;
			}
		};
		
		commentService.submitComment(SOME_BLOG_ID, "One flew over the cuckoo's nest.");
		
		new Verifications() {
			{
				mockUis.getCurrentUserId();
			}
		};
	}
}
