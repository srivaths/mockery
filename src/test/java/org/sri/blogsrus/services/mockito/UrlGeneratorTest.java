package org.sri.blogsrus.services.mockito;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.sri.blogsrus.services.UrlGenerator;
import org.sri.blogsrus.util.NetworkUtil;

@RunWith(PowerMockRunner.class)
@PrepareForTest(NetworkUtil.class)
public class UrlGeneratorTest {
	private static final String LOCALHOST = "localhost";
	private UrlGenerator generator;

	@Before
	public void setUp() {
		generator = new UrlGenerator();
	}

     @Test
	public void testGenerateURL() {
		PowerMockito.mockStatic(NetworkUtil.class);
		Mockito.when(NetworkUtil.getLocalHostname()).thenReturn(LOCALHOST);
		String generatedUrl = generator.generateURL();
		assertTrue("Expected URL", (UrlGenerator.HTTP_PROTOCOL_PREFIX + LOCALHOST + UrlGenerator.RESOURCE_NAME)
				.equals(generatedUrl));
		PowerMockito.verifyStatic();
		NetworkUtil.getLocalHostname();
	}
}