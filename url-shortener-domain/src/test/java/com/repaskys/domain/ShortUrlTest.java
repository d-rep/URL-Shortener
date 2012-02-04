package com.repaskys.domain;

import java.util.Set;

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
      assertEquals("Full URL is required", violation.getMessage());
      assertEquals("fullUrl", violation.getPropertyPath().toString());
   }

   @Test
   public void shortUrlCannotBeNull() {
      shortUrl.setFullUrl("google.com");
      constraintViolations = validator.validate(shortUrl);
      assertEquals(1, constraintViolations.size());
      violation = constraintViolations.iterator().next();
      assertEquals("Short URL is required", violation.getMessage());
      assertEquals("shortUrl", violation.getPropertyPath().toString());
   }

   @Test
   public void fullUrlCannotBeBlank() {
      shortUrl.setShortUrl("abcdef");
      shortUrl.setFullUrl("");
      constraintViolations = validator.validate(shortUrl);
      assertEquals(1, constraintViolations.size());
      violation = constraintViolations.iterator().next();
      assertEquals("Full URL cannot be blank and must be less than 500 characters long", violation.getMessage());
      assertEquals("fullUrl", violation.getPropertyPath().toString());
   }

   @Test
   public void shortUrlCannotBeBlank() {
      shortUrl.setShortUrl("");
      shortUrl.setFullUrl("google.com");
      constraintViolations = validator.validate(shortUrl);
      assertEquals(1, constraintViolations.size());
      violation = constraintViolations.iterator().next();
      assertEquals("Short URL cannot be blank and must be less than 20 characters long", violation.getMessage());
      assertEquals("shortUrl", violation.getPropertyPath().toString());
   }

}
