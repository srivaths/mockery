/**
 * 
 */
package org.sri.blogsrus.services.mockito;

import static org.powermock.api.mockito.PowerMockito.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.sri.blogsrus.dao.CommentDao;
import org.sri.blogsrus.exceptions.DatabaseConnectionException;
import org.sri.blogsrus.services.CommentService;

/**
 * Mocks a private method
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(CommentDao.class)
public class MockedPrivateMethod {
	private static final long MOCK_BLOG_ID = 89L;
	private static final String COMMENT_TEXT = "Static friction > Rolling friction";
	private CommentService commentService;
	private CommentDao mockDao;

	@Before
	public void setup() throws Exception {
		mockDao = new CommentDao();
		whenNew(CommentDao.class).withNoArguments().thenReturn(mockDao);
//		doThrow(new DatabaseConnectionException("Bad juju")).when(mockDao, "establishConnection");
		PowerMockito.when(mockDao, "establishConnection").thenThrow(new DatabaseConnectionException("Bad juju")
				);
		commentService = new CommentService();
	}
	
	@Test
	public final void failed_db_connection() throws Exception {
		
		commentService.submitComment(MOCK_BLOG_ID, COMMENT_TEXT);
		
		verifyPrivate(mockDao).invoke("establishConnection");
	}

}
