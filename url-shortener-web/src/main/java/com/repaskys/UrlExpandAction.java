package com.repaskys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.Preparable;

import com.repaskys.domain.ShortUrl;

public class UrlExpandAction implements Preparable {
   private static final Logger logger = LoggerFactory.getLogger(UrlExpandAction.class);

   private UrlShortenerService urlShortenerService;
   private String shortUrl;
   private String fullUrl;

   /**
    * This class must be aware of its Action Name, since we lookup the full URL using that shortened code.
    */
   private static String getActionName() {
      return ServletActionContext.getActionMapping().getName();
   }

   @Override
   public void prepare() {
      this.shortUrl = UrlExpandAction.getActionName();
   }

   public void setUrlShortenerService(UrlShortenerService urlShortenerService) {
      this.urlShortenerService = urlShortenerService;
   }

   public String getShortUrl() {
      return this.shortUrl;
   }

   public void setShortUrl(String shortUrl) {
      this.shortUrl = shortUrl;
   }

   public String getFullUrl() {
      return this.fullUrl;
   }

   public void setFullUrl(String fullUrl) {
      this.fullUrl = fullUrl;
   }

   public String execute() {
      logger.debug("My action name (the shortened URL) is: " + shortUrl);
      this.fullUrl = urlShortenerService.expandShortUrl(shortUrl);
      if(this.fullUrl != null && this.fullUrl.length() > 0) {
         return "SUCCESS";
      } else {
         return "ERROR";
      }
   }
}
