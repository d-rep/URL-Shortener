/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.repaskys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.Preparable;

/**
 * @author Drew Repasky
 */
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
