/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.repaskys.url.algorithms;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Drew Repasky
 */
public class AlphaNumericCodecTest {
	private Codec alphaNumericCodec;

	@Before
	public void setUp() {
		alphaNumericCodec = new AlphaNumericCodec();
	}

	@After
	public void tearDown() {
		alphaNumericCodec = null;
	}
	
	/**
	 * <pre>
	 * 125<sub>10</sub> (125 with a base of 10) = 2×62<sup>1</sup> + 1×62<sup>0</sup> = [2,1]
	 * 
	 * where: 
	 * 2 = c
	 * 1 = b
	 * </pre>
	 */
	@Test
	public void cbEncode() {
		String s = alphaNumericCodec.encode(125);
		assertEquals("cb", s);
	}
	
	@Test
	public void e9aEncode() {
		String s = alphaNumericCodec.encode(19158);
		assertEquals("e9a", s);
	}
	

	/**
	 * Test that values 0-25 map to a-z.
	 */
	@Test
	public void testEncodeSingleLowerCaseChar() {
		int encodeValue = 0;
		// map the integer to the corresponding ascii value (such as 98 is a 'b')
		for (char letter = 97; letter <= 122; letter++) {
			String expected = Character.toString(letter);
			assertEquals("The numeric value " + encodeValue
					+ " should be encoded to '" + expected + "'", expected,
					alphaNumericCodec.encode(encodeValue++));
		}
	}

	/**
	 * Test that values 26-51 map to A-Z.
	 */
	@Test
	public void testEncodeSingleUpperCaseChar() {
		int encodeValue = 26;
		// map the integer to the corresponding ascii value (such as 66 is a 'B')
		for (char letter = 65; letter <= 90; letter++) {
			String expected = Character.toString(letter);
			assertEquals("The numeric value " + encodeValue
					+ " should be encoded to '" + expected + "'", expected,
					alphaNumericCodec.encode(encodeValue++));
		}
	}
	
	/**
	 * Test that values 52-61 map to "0" to "9".
	 */
	@Test
	public void testNumber() {
		int encodeValue = 52;
		for(int i = 0; i<10; i++) {
			String expected = String.valueOf(i);
			assertEquals("The numeric value " + encodeValue
					+ " should be encoded to '" + expected + "'", expected,
					alphaNumericCodec.encode(encodeValue++));
		}
	}


	@Test
	public void testEncodeTwoUpperCaseProgression() {
		assertEquals("ba", alphaNumericCodec.encode(62));
		assertEquals("bb", alphaNumericCodec.encode(63));
		assertEquals("bc", alphaNumericCodec.encode(64));
		assertEquals("b9", alphaNumericCodec.encode(123));
		assertEquals("ca", alphaNumericCodec.encode(124));
		assertEquals("c9", alphaNumericCodec.encode(185));
	}
	
	
	@Test
	public void cbDecode() {
		int i = alphaNumericCodec.decode("cb");
		assertEquals(125, i);
	}
	
	@Test
	public void e9aDecode() {
		int i = alphaNumericCodec.decode("e9a");
		assertEquals(19158, i);
	}
	
	@Test
	public void testDecodeTwoUpperCaseProgression() {
		assertEquals(62, alphaNumericCodec.decode("ba"));
		assertEquals(63, alphaNumericCodec.decode("bb"));
		assertEquals(64, alphaNumericCodec.decode("bc"));
		assertEquals(123, alphaNumericCodec.decode("b9"));
		assertEquals(124, alphaNumericCodec.decode("ca"));
		assertEquals(185, alphaNumericCodec.decode("c9"));
	}

}