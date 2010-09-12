package org.sri.blogsrus.services.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.sri.blogsrus.services.CommentService;
import org.sri.blogsrus.util.Utility;

/**
 * Tests with <tt>static</tt>s mocked out.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Utility.class)
public class MockedUtilityTest {
	private static final long MOCK_BLOG_ID = 89L;
	private static final String COMMENT_TEXT = "Static friction > Rolling friction";
	private CommentService commentService;

	/**
	 * Test processing with {@link Utility#toSentences(String)} stubbed.
	 * @throws Exception
	 */
	
	@Test
	public final void mock_UtilityMethod() throws Exception {


		// Mock static methods in Utility
		PowerMockito.mockStatic(Utility.class);

		// Stub static call
		PowerMockito.when(Utility.toSentences(COMMENT_TEXT)).thenReturn(null);

		commentService = new CommentService();
		
		commentService.submitComment(MOCK_BLOG_ID, COMMENT_TEXT);
		
		// Verifications
		PowerMockito.verifyStatic(Mockito.times(1));
		Utility.toSentences(COMMENT_TEXT);
	}
	
}
