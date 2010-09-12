/**
 * 
 */
package org.sri.blogsrus.services.mockito;

import static org.mockito.Matchers.argThat;
import static org.powermock.api.mockito.PowerMockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.sri.blogsrus.dao.CommentDao;
import org.sri.blogsrus.data.Comment;
import org.sri.blogsrus.services.CommentService;

/**
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(CommentDao.class)
public class MockConstructor {
	private static final long SOME_BLOG_ID = 332L;
	private static final String SOME_TEXT = "Time flies like an arrow";
	private CommentService commentService;
	@Before
	public final void setup() throws Exception {
		commentService = new CommentService();
	}

	@Test
	public final void mock_the_Dao_Constructor() throws Exception {
		
		CommentDao mockDao = PowerMockito.mock(CommentDao.class);
		
		Mockito.when(
				mockDao.insertComment(argThat(new CommentMatcher())))
				.thenReturn(new Comment());

		whenNew(CommentDao.class).withNoArguments().thenReturn(mockDao);		
		
		commentService.submitComment(SOME_BLOG_ID, SOME_TEXT);
		
		verifyNew(Comment.class).withNoArguments();
	}
}