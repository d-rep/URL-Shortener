import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.lang.Iterable;

import domain.ShortUrl;
import repositories.UrlRepository;

public class UrlShortenerServiceImpl implements UrlShortenerService {

   private static final Logger logger = LoggerFactory.getLogger(UrlShortenerServiceImpl.class);

   @Autowired
   private UrlRepository urlRepository;

   public boolean saveUrl(ShortUrl shortUrl) {
      // FIXME handle exceptions
      urlRepository.save(shortUrl);
      return true;
   }

   public Iterable<ShortUrl> findAll() {
      return urlRepository.findAll();
   }

   public String expandShortUrl(String code) {
      ShortUrl shortUrl = urlRepository.findByShortUrl(code);
      return (shortUrl != null) ? shortUrl.getFullUrl() : "";
   }

}
