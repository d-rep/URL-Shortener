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

import java.lang.Iterable;
import com.repaskys.domain.ShortUrl;

/**
 * @author Drew Repasky
 */
public class UrlShortenerAdminAction {

   private UrlShortenerService urlShortenerService;
   private Iterable<ShortUrl> shortUrlList;

   public void setUrlShortenerService(UrlShortenerService urlShortenerService) {
      this.urlShortenerService = urlShortenerService;
   }

   public Iterable<ShortUrl> getShortUrlList() {
      return this.shortUrlList;
   }

   public void setShortUrlList(Iterable<ShortUrl> shortUrlList) {
      this.shortUrlList = shortUrlList;
   }

   public String execute() {
      shortUrlList = urlShortenerService.findAll();
      return "INPUT";
   }

}
