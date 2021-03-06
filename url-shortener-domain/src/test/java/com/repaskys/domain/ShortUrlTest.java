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
package com.repaskys.domain;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.repaskys.domain.ShortUrl;

/**
 * Unit tests for the ShortUrl entity class.
 *
 * @author Drew Repasky
 */
public class ShortUrlTest {
   private static Validator validator;
   private ShortUrl shortUrl;
   private ConstraintViolation<ShortUrl> violation;
   private Set<ConstraintViolation<ShortUrl>> constraintViolations;

   @Before
   public void setUp() {
      shortUrl = new ShortUrl();
   }

   @After
   public void tearDown() {
      shortUrl = null;
   }

   @BeforeClass
   public static void oneTimeSetUp() {
      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
      validator = factory.getValidator();
   }

   @Test
   public void testValidShortUrl() {
      shortUrl.setFullUrl("http://www.google.com");
      assertEquals("http://www.google.com", shortUrl.getFullUrl());
      constraintViolations = validator.validate(shortUrl);
      assertTrue(constraintViolations.isEmpty());
   }

   @Test
   public void fullUrlCannotBeNull() {
      constraintViolations = validator.validate(shortUrl);
      assertEquals(1, constraintViolations.size());
      violation = constraintViolations.iterator().next();
      assertEquals("Full URL cannot be blank", violation.getMessage());
      assertEquals("fullUrl", violation.getPropertyPath().toString());
   }

   @Test
   public void fullUrlCannotBeBlank() {
      shortUrl.setFullUrl("");
      constraintViolations = validator.validate(shortUrl);
      assertEquals(1, constraintViolations.size());
      violation = constraintViolations.iterator().next();
      assertEquals("Full URL cannot be blank", violation.getMessage());
      assertEquals("fullUrl", violation.getPropertyPath().toString());
   }

   @Test
   public void fullUrlCannotBeOnlySpaces() {
		Set<String> expectedErrors = new HashSet<String>() {
			private static final long serialVersionUID = 6480942588471789481L;
			{
				add("Full URL cannot be blank");
				add("Expected a URL (example: http://www.google.com)");
			}
		};

      shortUrl.setFullUrl(" ");

      // this is unordered, so we compare 2 HashSets of only the error messages
      constraintViolations = validator.validate(shortUrl);

      Set<String> actualErrors = new HashSet<String>();
      for(ConstraintViolation<ShortUrl> violation: constraintViolations) {
         actualErrors.add(violation.getMessage());
         assertEquals("fullUrl", violation.getPropertyPath().toString());
      }
      assertEquals(expectedErrors, actualErrors);
   }

   @Test
   public void fullUrlTooLong() {
      shortUrl.setFullUrl("http://www." + StringUtils.repeat("B", 486) + ".com");
      constraintViolations = validator.validate(shortUrl);
      assertEquals(1, constraintViolations.size());
      violation = constraintViolations.iterator().next();
      assertEquals("Full URL must be less than 500 characters long", violation.getMessage());
      assertEquals("fullUrl", violation.getPropertyPath().toString());
   }

   @Test
   public void fullUrlMustHaveHttpPrefix() {
      shortUrl.setFullUrl("www.google.com");
      constraintViolations = validator.validate(shortUrl);
      assertEquals(1, constraintViolations.size());
      violation = constraintViolations.iterator().next();
      assertEquals("Expected a URL (example: http://www.google.com)", violation.getMessage());
      assertEquals("fullUrl", violation.getPropertyPath().toString());
   }

   @Test
   public void fullUrlCannotAllowXSSChars() {
      shortUrl.setFullUrl("www.g<script>oogle.com");
      constraintViolations = validator.validate(shortUrl);
      assertEquals(1, constraintViolations.size());
      violation = constraintViolations.iterator().next();
      assertEquals("Expected a URL (example: http://www.google.com)", violation.getMessage());
      assertEquals("fullUrl", violation.getPropertyPath().toString());
   }

   @Test
   public void fullUrlCannotAllowSQLInjectionChars() {
      shortUrl.setFullUrl("www.goo'gle.com");
      constraintViolations = validator.validate(shortUrl);
      assertEquals(1, constraintViolations.size());
      violation = constraintViolations.iterator().next();
      assertEquals("Expected a URL (example: http://www.google.com)", violation.getMessage());
      assertEquals("fullUrl", violation.getPropertyPath().toString());
   }

   @Test
   public void fullUrlStillValidWithoutWWW() {
      shortUrl.setFullUrl("http://google.com");
      constraintViolations = validator.validate(shortUrl);
      assertEquals(0, constraintViolations.size());
   }

   @Test
   public void fullUrlStillValidWithParameters() {
      shortUrl.setFullUrl("http://google.com?q=repasky");
      constraintViolations = validator.validate(shortUrl);
      assertEquals(0, constraintViolations.size());
   }

   // TODO The @URL validation doesn't require a domain postfix (such as ".com").  We may want to add a regexp to check for the case where a user leaves it off.
   @Test
   public void fullUrlMustHaveDomainPostfix() {
      shortUrl.setFullUrl("http://www.google");
      constraintViolations = validator.validate(shortUrl);
      assertEquals(0, constraintViolations.size());
   }

   @Test
   public void fullUrlCanHaveUncommonDomainPostfixBiz() {
      shortUrl.setFullUrl("http://repasky.biz");
      constraintViolations = validator.validate(shortUrl);
      assertEquals(0, constraintViolations.size());
   }

   @Test
   public void fullUrlCanHaveUncommonDomainPostfixInfo() {
      shortUrl.setFullUrl("http://repasky.info");
      constraintViolations = validator.validate(shortUrl);
      assertEquals(0, constraintViolations.size());
   }

   @Test
   public void fullUrlCanHaveUncommonDomainPostfixName() {
      shortUrl.setFullUrl("http://repasky.name");
      constraintViolations = validator.validate(shortUrl);
      assertEquals(0, constraintViolations.size());
   }

   @Test
   public void fullUrlCanHaveUncommonDomainPostfixLy() {
      shortUrl.setFullUrl("http://bit.ly");
      constraintViolations = validator.validate(shortUrl);
      assertEquals(0, constraintViolations.size());
   }
   
   @Test
   public void shortCodeGenerated() {
	   Long id = new Long(125);
	   shortUrl.setId(id);
	   assertEquals(id, shortUrl.getId());
	   assertEquals("cb", shortUrl.getShortUrl());
   }

}
