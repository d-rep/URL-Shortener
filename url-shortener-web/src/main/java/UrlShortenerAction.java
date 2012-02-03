import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.ShortUrl;

public class UrlShortenerAction {
   private static final Logger logger = LoggerFactory.getLogger(UrlShortenerAction.class);
   private UrlShortenerService urlShortenerService;
   private ShortUrl shortUrl;
   private List<String> violations;

   public void setUrlShortenerService(UrlShortenerService urlShortenerService) {
      this.urlShortenerService = urlShortenerService;
   }

   public ShortUrl getShortUrl() {
      return shortUrl;
   }

   public void setShortUrl(ShortUrl shortUrl) {
      this.shortUrl = shortUrl;
   }

   public List<String> getViolations() {
      return this.violations;
   }

   public String execute() {
      violations = urlShortenerService.validateShortUrl(shortUrl);
      String returnCode = "ERROR";

      if(violations.isEmpty()) {
         urlShortenerService.saveUrl(shortUrl);
         returnCode = "SUCCESS";
      } else {
         logger.debug("Did not save due to validation errors: " + violations);
      }
      return returnCode;
   }
}
