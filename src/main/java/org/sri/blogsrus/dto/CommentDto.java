/**
 * 
 */
package org.sri.blogsrus.dto;

/**
 * Data transfer object for a {@link Comment}.
 */
public class CommentDto {
	private String id;
	public CommentDto() {}
	public CommentDto(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "CommentDto: ID=" + id;
	}
}
