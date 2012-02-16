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
import java.util.List;

import com.repaskys.domain.ShortUrl;

/**
 * This class is for saving and finding ShortUrl instances.  It can also be used to expand a short code into a full URL.
 *
 * @author Drew Repasky
 */
public interface UrlShortenerService {
   /**
    * Validate the ShortURL and return a list of violations.
    */
   List<String> validateShortUrl(ShortUrl shortUrl);

   /**
    * Save the ShortURL and return an error string.  Returns a blank string if the save was successful.
    */
   String saveUrl(ShortUrl shortUrl);
   Iterable<ShortUrl> findAll();
   String expandShortUrl(String shortUrl);
}
