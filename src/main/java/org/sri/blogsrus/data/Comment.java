package org.sri.blogsrus.data;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.sri.blogsrus.dto.CommentDto;

/**
 * Class corresponding to a record from the COMMENTS table.
 */
public class Comment {
	private Long commentId;
	private Long blogId;
	private Long authorId;
	private String text;
	private Date timestamp;
	public Comment() {
		
	}
	public Comment(Long blog, Long author, String theText, Date timestamp) {
		setArticleId(blog);
		setAuthorId(author);
		setText(theText);
		setTimestamp(timestamp);
	}
	public Long getCommentId() {
		return commentId;
	}
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}
	public Long getArticleId() {
		return blogId;
	}
	public void setArticleId(Long articleId) {
		this.blogId = articleId;
	}
	public Long getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	public CommentDto toDto() {
		return new CommentDto();
	}
}
