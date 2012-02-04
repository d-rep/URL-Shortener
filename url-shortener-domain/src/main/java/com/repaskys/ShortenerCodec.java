package com.repaskys;

public interface ShortenerCodec {
   // TODO should switch to long primitive type
   String encode(int id);
   long decode(String code);
}
