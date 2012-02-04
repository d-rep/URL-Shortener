package com.repaskys;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ShortenerCodecTest {
   private ShortenerCodec shortenerCodec;

   @Before
   public void setUp() {
      shortenerCodec = new ShortenerCodecImpl();
   }

   @After
   public void tearDown() {
      shortenerCodec = null;
   }

   // Values 1-26 map to A-Z
   @Test
   public void testEncodeSingleUpperCaseChar() {
      int encodeValue = 1;
      // map the integer to the corresponding ascii value (such as 66 is a 'B')
      for(char letter=65; letter<=90; letter++) {
         String expected = Character.toString(letter);
         assertEquals("The numeric value " + encodeValue + " should be encoded to '" + expected + "'", expected, shortenerCodec.encode(encodeValue++));
      }
   }


   // Values 26-52 map to a-z
   @Test
   public void testEncodeSingleLowerCaseChar() {
      int encodeValue = 27;
      // map the integer to the corresponding ascii value (such as 98 is a 'b')
      for(char letter=97; letter<=122; letter++) {
         String expected = Character.toString(letter);
         assertEquals("The numeric value " + encodeValue + " should be encoded to '" + expected + "'", expected, shortenerCodec.encode(encodeValue++));
      }
   }

   @Test
   public void testEncodeTwoUpperCase() {
      assertEquals("expected AA", "AA", shortenerCodec.encode(53));
      assertEquals("AB", shortenerCodec.encode(54));
      assertEquals("AC", shortenerCodec.encode(55));
      assertEquals("Az", shortenerCodec.encode(104));
      assertEquals("BA", shortenerCodec.encode(105));
      assertEquals("BC", shortenerCodec.encode(107));
      assertEquals("Cz", shortenerCodec.encode(208));
      assertEquals("DA", shortenerCodec.encode(209));
      assertEquals("SL", shortenerCodec.encode(1000));
   }

}
