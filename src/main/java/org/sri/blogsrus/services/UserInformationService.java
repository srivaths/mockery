package org.sri.blogsrus.services;

/**
 * Provide user related services.
 */
public class UserInformationService {
	
	private DirectoryService directory;
	
	public UserInformationService() {
		directory = new DirectoryService();
	}

	/**
	 * Returns the user id corresponding to the current user.
	 * @return A <tt>Long</tt> representing a userid.
	 */
	public Long getCurrentUserId() {
		return null;
	}

}
