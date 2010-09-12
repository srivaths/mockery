package org.sri.blogsrus.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.sri.blogsrus.data.Comment;
import org.sri.blogsrus.exceptions.DatabaseConnectionException;
import org.sri.blogsrus.exceptions.WriteException;
import org.sri.blogsrus.util.SequenceNumberGenerator;

public class CommentDao {
	private final Logger logger = Logger.getLogger(CommentDao.class);
	private static final String JDBC_URL = "jdbc:oracle:thin@blogs.chatty.net:8892:ORBD";
	private Connection connection;

	public CommentDao() throws DatabaseConnectionException {

		// establishConnection();
	}

	private void establishConnection() 
				throws DatabaseConnectionException {
		try {
			spring_setup();
			hibernate_setup();
		} catch (Exception e) {
			new DatabaseConnectionException(e.getMessage());
		}
	}

	private void establishConnectionx() throws DatabaseConnectionException {
		try {
			Class.forName("oracle.driver.JDBCDriver");
		} catch (ClassNotFoundException e) {
			throw new DatabaseConnectionException("Driver class not found.");
		}
		try {
			connection = DriverManager.getConnection(JDBC_URL);
		} catch (SQLException e) {
			throw new DatabaseConnectionException(
					"Unable to establish a connection to " + JDBC_URL);
		}
	}

	/**
	 * Inserts the given comment as a record to the COMMENT table.
	 * 
	 * @param comment
	 * @throws WriteException
	 */
	
	public Comment insertComment(Comment comment) 
								throws WriteException {
		logger.trace("Inserting comment: " + comment);

		try {
			establishConnection();
		} catch (DatabaseConnectionException e) {
			String m = "Unable to save your comment at this time.";
			logger.error(m, e);
			throw new WriteException(m);
		}

		try {
			Statement statement = connection
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
			statement.executeUpdate("insert into COMMENTS values ("
					+ getCommentId() + ", " + comment.getArticleId() + ", "
					+ comment.getAuthorId() + ", " + comment.getText() + ", "
					+ comment.getTimestamp() + ")");
		} catch (Exception e) {
			String m = "Unable to save your comment at this time.";
			logger.error(m, e);
			throw new WriteException(m);
		}

		return comment;
	}

	private long getCommentId() {
		return SequenceNumberGenerator.nextVal();
	}

	private void spring_setup() throws Exception {

	}

	private void hibernate_setup() {

	}
}
