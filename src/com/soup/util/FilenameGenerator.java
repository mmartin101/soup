package com.soup.util;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
/**
 * FilenameGenerator generates filenames
 *
 */
public final class FilenameGenerator
{
	private SecureRandom random = new SecureRandom();
	
	/**
	 * generateFilename returns a string consisting of 8 random characters a-z, A-Z, or 0-9
	 *
	 */
	public String generateFilename()
	{
		return new BigInteger(40, random).toString(32);
	}
}
