import org.junit.Test;
import static org.junit.Assert.*;

public class ShortUrlTest {
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
}
