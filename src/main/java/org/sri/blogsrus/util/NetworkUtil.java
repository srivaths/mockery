/* $Id$ */
package org.sri.blogsrus.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public final class NetworkUtil {
	public static String getLocalHostname() {
		String hostname = "";
		try {
			InetAddress addr = InetAddress.getLocalHost();
			// Get hostname
			hostname = addr.getHostName();
		} catch(UnknownHostException e) {
		}
		return hostname;
	}
}