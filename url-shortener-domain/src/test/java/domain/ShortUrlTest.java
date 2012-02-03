import java.util.Set;

import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import domain.ShortUrl;

public class ShortUrlTest {
   private static Validator validator;

   @BeforeClass
   public static void setUp() {
      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
      validator = factory.getValidator();
   }

   @Test
   public void testCanary() {
      assertTrue("If this fails, it's time to panic", true);
   }

   @Test
   public void testGettersAndSetters() {
      ShortUrl shortUrl = new ShortUrl();
      shortUrl.setShortUrl("abcdef");
      shortUrl.setFullUrl("http://www.google.com");
      assertEquals("abcdef", shortUrl.getShortUrl());
      assertEquals("http://www.google.com", shortUrl.getFullUrl());
   }

   @Test
   public void fullUrlCannotBeNull() {
      ShortUrl shortUrl = new ShortUrl();
      shortUrl.setShortUrl("abcdef");
      Set<ConstraintViolation<ShortUrl>> constraintViolations = validator.validate(shortUrl);
      assertEquals(1, constraintViolations.size());
      assertEquals("String is required and cannot be blank", constraintViolations.iterator().next().getMessage());

   }
}
