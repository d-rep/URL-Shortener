import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import domain.ShortUrl;
import repositories.UrlRepository;

public class UrlShortenerServiceImpl implements UrlShortenerService {

   private static final Logger logger = LoggerFactory.getLogger(UrlShortenerServiceImpl.class);

   @Autowired
   private UrlRepository urlRepository;

   public boolean saveUrl(ShortUrl shortUrl) {
      logger.error("I still haven't been implemented yet!");
      urlRepository.save(shortUrl);
      logger.error("AFTER SAVE");
      return true;
   }
}
