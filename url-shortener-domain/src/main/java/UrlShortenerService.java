import java.lang.Iterable;
import java.util.List;

import domain.ShortUrl;

public interface UrlShortenerService {
   /**
    * Validate the ShortURL and return a list of violations.
    */
   List<String> validateShortUrl(ShortUrl shortUrl);
   boolean saveUrl(ShortUrl shortUrl);
   Iterable<ShortUrl> findAll();
   String expandShortUrl(String shortUrl);
}
