import java.lang.Iterable;

import domain.ShortUrl;

public interface UrlShortenerService {
   boolean saveUrl(ShortUrl shortUrl);
   Iterable<ShortUrl> findAll();
   String expandShortUrl(String shortUrl);
}
