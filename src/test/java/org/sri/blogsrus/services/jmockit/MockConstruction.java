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
public class MockConstruction {
	private static final Long MOCK_USER_ID = 17L;
	private static final long SOME_BLOG_ID = 332L;
	@Mocked private UserInformationService mockUserInformationService;
	@Mocked private CommentDao mockDao;
	private CommentService commentService;
	
	@Before
	public void setup() throws DatabaseConnectionException {
		commentService = new CommentService();
		commentService.setUserInformationService(mockUserInformationService);
	}

	@Test
	public final void private_parts() 
					throws DatabaseConnectionException {
		new Expectations() {
			{
				invoke(mockDao, "establishConnection");
				result = new DatabaseConnectionException("Bad juju");
			}
		};
		
		commentService.setUserInformationService(mockUserInformationService);
//		commentService.submitComment(SOME_BLOG_ID, 
//				"One flew over the cuckoo's nest.");
		
		new Verifications() {
			{
				mockUserInformationService.getCurrentUserId();
			}
		};
	}
}
