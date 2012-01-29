import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.struts2.ServletActionContext;

import domain.ShortUrl;

public class UrlExpandAction {
   private static final Logger logger = LoggerFactory.getLogger(UrlExpandAction.class);

   private UrlShortenerService urlShortenerService;
   private String fullUrl;

   public void setUrlShortenerService(UrlShortenerService urlShortenerService) {
      this.urlShortenerService = urlShortenerService;
   }

   public String getFullUrl() {
      return this.fullUrl;
   }

   public void setFullUrl(String fullUrl) {
      this.fullUrl = fullUrl;
   }

   /**
    * This class must be aware of its Action Name, since we lookup the full URL using that shortened code.
    */
	public String getActionName() {
		return ServletActionContext.getActionMapping().getName();
	}

   public String execute() {
      String shortUrl = this.getActionName();
      logger.debug("My action name (the shortened URL) is: " + shortUrl);
      this.fullUrl = urlShortenerService.expandShortUrl(shortUrl);
      if(this.fullUrl != null && this.fullUrl.length() > 0) {
         return "SUCCESS";
      } else {
         return "ERROR";
      }
   }
}
