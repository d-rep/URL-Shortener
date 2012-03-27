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

/**
 * Bijective function that will take an integer and map it to an alphanumeric string.
 * 
 * <pre>
 * 0 -> a
 * 1 -> b
 * ...
 * 25 -> z
 * ...
 * 52 -> 0
 * 61 -> 9
 * </pre>
 * 
 * @see http://stackoverflow.com/questions/742013/how-to-code-a-url-shortener
 * @see https://gist.github.com/1073996
 * 
 * @author Drew Repasky
 */
public class AlphaNumericCodec implements Codec {
	
	private static final String ALPHABET = 
		"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	private static final int ALPHABET_LENGTH = ALPHABET.length();
	private static final char[] CHARSET = ALPHABET.toCharArray();
	
	public String encode(int i) {
		
		if(i == 0) {
			return Character.toString(CHARSET[0]);
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		while(i > 0) {
			int remainder = i % ALPHABET_LENGTH;
			i /= ALPHABET_LENGTH;
			stringBuilder.append(CHARSET[remainder]);
		}
		return stringBuilder.reverse().toString();
	}

	@Override
	public int decode(String s) {
		int i = 0;
		char[] chars = s.toCharArray();
		for(char c : chars) {
			i = i * ALPHABET_LENGTH + ALPHABET.indexOf(c);
		}
		return i;
	}

}
