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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.lang.Iterable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;

import com.repaskys.domain.ShortUrl;
import com.repaskys.repositories.UrlRepository;

/**
 * @author Drew Repasky
 */
public class UrlShortenerServiceImpl implements UrlShortenerService {

   private static final Logger logger = LoggerFactory.getLogger(UrlShortenerServiceImpl.class);

   @Autowired
   private UrlRepository urlRepository;

   @Autowired
   private Validator validator;

   public List<String> validateShortUrl(ShortUrl shortUrl) {
      List<String> violations = new ArrayList<String>();
      Set<ConstraintViolation<ShortUrl>> constraintViolations = validator.validate(shortUrl);
      for(ConstraintViolation constraintViolation : constraintViolations) {
         violations.add(constraintViolation.getMessage());
      }
      return violations;
   }

   public String saveUrl(ShortUrl shortUrl) {
      String errorMessage = "";
      ShortUrl savedShortUrl = null;
      try {
         savedShortUrl = urlRepository.save(shortUrl);
      } catch(DataAccessException ex) {
         logger.error("DataAccessException when saving ShortUrl", ex);
         errorMessage = ex.getMessage();
      }
      return errorMessage;
   }

   public Iterable<ShortUrl> findAll() {
      return urlRepository.findAll();
   }

   public String expandShortUrl(String code) {
      ShortUrl shortUrl = urlRepository.findByShortUrl(code);
      return (shortUrl != null) ? shortUrl.getFullUrl() : "";
   }

}
