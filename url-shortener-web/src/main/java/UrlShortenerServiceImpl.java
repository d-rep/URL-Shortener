import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UrlShortenerServiceImpl implements UrlShortenerService {

   private static final Logger logger = LoggerFactory.getLogger(UrlShortenerServiceImpl.class);

   public boolean saveUrl(ShortUrl shortUrl) {
      logger.trace("executing inside UrlShortenerServiceImpl.saveUrl");
      return true;
   }
}
