package com.soup.util;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

public final class FilenameGenerator
{
	private SecureRandom random = new SecureRandom();
	
	public String generateFilename()
	{
		return new BigInteger(40, random).toString(32);
	}
}
