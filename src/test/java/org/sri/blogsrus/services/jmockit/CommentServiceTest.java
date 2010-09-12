/**
 * 
 */
package org.sri.blogsrus.services.jmockit;

import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;

import org.junit.Before;
import org.junit.Test;
import org.sri.blogsrus.exceptions.DatabaseConnectionException;
import org.sri.blogsrus.exceptions.WriteException;
import org.sri.blogsrus.services.CommentService;
import org.sri.blogsrus.services.UserInformationService;

/**
 * Tests of {@link CommentService} using JMockit.
 */
public class CommentServiceTest {
	private static final Long MOCK_USER_ID = 17L;
	private static final long SOME_BLOG_ID = 332L;
	@Mocked private UserInformationService mockUserInformationService;
	private CommentService commentService;
	
	@Before
	public void setup() throws DatabaseConnectionException {
		commentService = new CommentService();
		commentService.setUserInformationService(mockUserInformationService);
	}

	@Test
	public final void someTest() throws WriteException {
		new Expectations() {
			{
				mockUserInformationService.getCurrentUserId(); result = MOCK_USER_ID;
			}
		};
		
		commentService.setUserInformationService(mockUserInformationService);
		commentService.submitComment(SOME_BLOG_ID, "One flew over the cuckoo's nest.");
		
		new Verifications() {
			{
				mockUserInformationService.getCurrentUserId();
			}
		};
	}
}
