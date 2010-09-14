/* $Id$ */
package org.sri.blogsrus.services;

import org.apache.log4j.Logger;
import org.sri.blogsrus.constants.Operation;

/**
 * Audit tracking services
 */
public class AuditService {
	private static final Logger logger = Logger.getLogger(AuditService.class);

	public void audit(Operation operation, Long userId) {
		logger.debug("Audting " + operation.name() + " by " + userId);
	}
}
