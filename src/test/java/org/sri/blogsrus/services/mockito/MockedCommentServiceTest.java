package org.sri.blogsrus.services.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.sri.blogsrus.data.Comment;
import org.sri.blogsrus.services.CommentService;
import org.sri.blogsrus.services.UserInformationService;
import org.sri.blogsrus.util.Utility;

/**
 * Tests with <tt>static</tt>s mocked out.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(CommentService.class)
public class MockedCommentServiceTest {
	private static final long MOCK_BLOG_ID = 89L;
	private static final String COMMENT_TEXT = "Static friction > Rolling friction";
	private static final String COMMENT_SERVICE_FACTORY_METHOD = "createInstance";
	private static final Long MOCK_USER_ID = 17L;
	@Mock
	private UserInformationService mockUserInformationService;
	private CommentService commentService;

	@Test
	public final void verify_privateParts() throws Exception {
		
		commentService = PowerMockito.spy(new CommentService());
		commentService.setUserInformationService(mockUserInformationService);
		
		commentService.submitComment(89L, COMMENT_TEXT);
		
		PowerMockito.verifyPrivate(CommentService.class).invoke("createInstance", Mockito.anyLong(), Mockito.anyLong(), Mockito.anyString());
		PowerMockito.verifyPrivate(commentService, Mockito.times(7)).invoke("foo");
	}

}
