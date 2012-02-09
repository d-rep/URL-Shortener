package com.repaskys;

import java.lang.Iterable;
import java.util.List;

import com.repaskys.domain.ShortUrl;

public interface UrlShortenerService {
   /**
    * Validate the ShortURL and return a list of violations.
    */
   List<String> validateShortUrl(ShortUrl shortUrl);

   /**
    * Save the ShortURL and return an error string.  Returns a blank string if the save was successful.
    */
   String saveUrl(ShortUrl shortUrl);
   Iterable<ShortUrl> findAll();
   String expandShortUrl(String shortUrl);
}
