package org.sri.blogsrus.services;

import org.sri.blogsrus.util.NetworkUtil;

public class UrlGenerator {
	public static final String HTTP_PROTOCOL_PREFIX = "http://";
	public static final String RESOURCE_NAME = "/myapplication/images/myimage.gif";

	public String generateURL() {
		return HTTP_PROTOCOL_PREFIX + NetworkUtil.getLocalHostname() + RESOURCE_NAME;
	}

}