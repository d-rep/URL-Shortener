package com.repaskys;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.repaskys.domain.ShortUrl;

public class UrlShortenerAction {
   private static final Logger logger = LoggerFactory.getLogger(UrlShortenerAction.class);
   private UrlShortenerService urlShortenerService;
   private ShortUrl shortUrl;
   private List<String> violations = new ArrayList<String>();

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
         String errorMessage = urlShortenerService.saveUrl(shortUrl);
         if(StringUtils.isBlank(errorMessage)) {
            returnCode = "SUCCESS";
         } else {
            violations.add(errorMessage);
            returnCode = "ERROR";
         }
      } else {
         logger.debug("Did not save due to validation errors: " + violations);
      }
      return returnCode;
   }
}
