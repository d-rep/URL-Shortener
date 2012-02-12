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
package com.repaskys;

import java.util.List;
import java.util.Arrays;

/**
 * @author Drew Repasky
 */
public class ShortenerCodecImpl implements ShortenerCodec {

   private static final char[] MAPPING = 
      {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
      'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

   private static char getMapping(int index) {
      return MAPPING[index];
   }

   @Override
   public String encode(int num) {

      StringBuffer letters = new StringBuffer("");

      // FIXME: algorithm doesn't work past 2 alphabetic-characters
      int div = num / 52;
      int mod = num % 52;

      char letter = ' ';
      if(div > 0) {
         if(mod != 0) {
            letter = getMapping(div-1);
            letters.append(letter);
         } else if(div > 1) {
            letter = getMapping(div-2);
            letters.append(letter);
         }
      }

      if(mod > 0) {
         letter = getMapping(mod-1);
         letters.append(letter);
      } else if(mod == 0) {
         letter = getMapping(51);
         letters.append(letter);
      }

      return letters.toString();
   }

   @Override
   public long decode(String code) {
      return 0;
   }
}
