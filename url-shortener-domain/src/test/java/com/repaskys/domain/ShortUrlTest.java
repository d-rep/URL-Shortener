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
   public void testFullUrlHttpPrefix() {
      shortUrl.setFullUrl("www.google.com");
      assertEquals("http://www.google.com", shortUrl.getFullUrl());
   }

   @Test
   public void testValidShortUrl() {
      shortUrl.setShortUrl("abcdef");
      shortUrl.setFullUrl("http://www.google.com");
      assertEquals("abcdef", shortUrl.getShortUrl());
      assertEquals("http://www.google.com", shortUrl.getFullUrl());

      constraintViolations = validator.validate(shortUrl);
      assertTrue(constraintViolations.isEmpty());
   }

   @Test
   public void fullUrlCannotBeNull() {
      shortUrl.setShortUrl("abcdef");
      constraintViolations = validator.validate(shortUrl);
      assertEquals(1, constraintViolations.size());
      violation = constraintViolations.iterator().next();
      assertEquals("Full URL cannot be blank", violation.getMessage());
      assertEquals("fullUrl", violation.getPropertyPath().toString());
   }

   @Test
   public void shortUrlCannotBeNull() {
      shortUrl.setFullUrl("google.com");
      constraintViolations = validator.validate(shortUrl);
      assertEquals(1, constraintViolations.size());
      violation = constraintViolations.iterator().next();
      assertEquals("Short URL cannot be blank", violation.getMessage());
      assertEquals("shortUrl", violation.getPropertyPath().toString());
   }

   @Test
   public void fullUrlCannotBeBlank() {
      shortUrl.setShortUrl("abcdef");
      shortUrl.setFullUrl("");
      constraintViolations = validator.validate(shortUrl);
      assertEquals(1, constraintViolations.size());
      violation = constraintViolations.iterator().next();
      assertEquals("Full URL cannot be blank", violation.getMessage());
      assertEquals("fullUrl", violation.getPropertyPath().toString());
   }

   @Test
   public void shortUrlCannotBeBlank() {
      shortUrl.setShortUrl("");
      shortUrl.setFullUrl("google.com");
      constraintViolations = validator.validate(shortUrl);
      assertEquals(1, constraintViolations.size());
      violation = constraintViolations.iterator().next();
      assertEquals("Short URL cannot be blank", violation.getMessage());
      assertEquals("shortUrl", violation.getPropertyPath().toString());
   }

   @Test
   public void fullUrlCannotBeOnlySpaces() {
      shortUrl.setShortUrl("abcdef");
      shortUrl.setFullUrl(" ");
      constraintViolations = validator.validate(shortUrl);
      assertEquals(1, constraintViolations.size());
      violation = constraintViolations.iterator().next();
      assertEquals("Full URL cannot be blank", violation.getMessage());
      assertEquals("fullUrl", violation.getPropertyPath().toString());
   }

   @Test
   public void shortUrlCannotOnlySpaces() {
      shortUrl.setShortUrl(" ");
      shortUrl.setFullUrl("google.com");
      constraintViolations = validator.validate(shortUrl);
      assertEquals(1, constraintViolations.size());
      violation = constraintViolations.iterator().next();
      assertEquals("Short URL cannot be blank", violation.getMessage());
      assertEquals("shortUrl", violation.getPropertyPath().toString());
   }

   @Test
   public void fullUrlTooLong() {
      shortUrl.setShortUrl("abcdef");
      shortUrl.setFullUrl(StringUtils.repeat("B", 501));
      constraintViolations = validator.validate(shortUrl);
      assertEquals(1, constraintViolations.size());
      violation = constraintViolations.iterator().next();
      assertEquals("Full URL must be less than 500 characters long", violation.getMessage());
      assertEquals("fullUrl", violation.getPropertyPath().toString());
   }

   @Test
   public void shortUrlCannotTooLong() {
      shortUrl.setShortUrl(StringUtils.repeat("A", 21));
      shortUrl.setFullUrl("google.com");
      constraintViolations = validator.validate(shortUrl);
      assertEquals(1, constraintViolations.size());
      violation = constraintViolations.iterator().next();
      assertEquals("Short URL must be less than 20 characters long", violation.getMessage());
      assertEquals("shortUrl", violation.getPropertyPath().toString());
   }

}
