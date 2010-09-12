package org.sri.blogsrus.services;

import java.util.Date;

import org.apache.log4j.Logger;
import org.sri.blogsrus.constants.Operation;
import org.sri.blogsrus.dao.CommentDao;
import org.sri.blogsrus.data.Comment;
import org.sri.blogsrus.dto.CommentDto;
import org.sri.blogsrus.exceptions.DatabaseConnectionException;
import org.sri.blogsrus.exceptions.WriteException;
import org.sri.blogsrus.util.Utility;

/**
 * Public interface for comment-related operations.
 */
public class CommentService {
	private static final Logger logger = Logger.getLogger(CommentService.class);
	private UserInformationService userInformationService;
	private CommentDao commentDao;
	private AuditService auditService;

	public CommentService() throws DatabaseConnectionException {
		setUserInformationService(new UserInformationService());
		setCommentDao(new CommentDao());
		setAuditService(new AuditService());
	}

	public UserInformationService getUserInformationService() {
		return userInformationService;
	}

	public void setUserInformationService(UserInformationService userInformationService) {
		this.userInformationService = userInformationService;
	}

	public CommentDao getCommentDao() {
		return commentDao;
	}

	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}
	
	/**
	 * @return the auditService
	 */
	public AuditService getAuditService() {
		return auditService;
	}

	/**
	 * @param auditService
	 *            the auditService to set
	 */
	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}

	private static Comment createInstance(Long blog, Long author, String theText) {
		Comment comment = new Comment(blog, author, theText, new Date());
		
		return comment;
	}
	
	/**
	 * Adds the given text as a comment for the specified article.
	 * 
	 * @param blogId
	 *            The article ID
	 * @param text
	 *            The text to add.
	 * @throws WriteException 
	 */

	public final CommentDto submitComment(Long blogId, String text) 
												throws WriteException {
		
		text = Utility.toSentences(text);
		
		if(Utility.isEmpty(text)) {
			logger.info("Empty comments not allowed.");
			return null;
		}
		
		Long userId = userInformationService.getCurrentUserId();
		
		Comment comment = createInstance(blogId, userId, text);
		logger.debug("Saving comment: " + comment);
		Comment insertedComment = commentDao.insertComment(comment);
		auditService.audit(Operation.NEW_COMMENT, userId);

		logger.debug("Inserted comment " + insertedComment.getCommentId());
		return comment.toDto();
	}
	
	public final void deleteComment(Long commentId) {
		// TODO
	}
}
