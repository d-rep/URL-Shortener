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
package com.repaskys.actions;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.repaskys.domain.ShortUrl;
import com.repaskys.UrlShortenerService;

/**
 * This is a Struts2 Action class, which is used to add new Short URLs.
 *
 * @author Drew Repasky
 */
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
