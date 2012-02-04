package com.repaskys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

public class UrlShortenerServiceImpl implements UrlShortenerService {

   private static final Logger logger = LoggerFactory.getLogger(UrlShortenerServiceImpl.class);

   @Autowired
   private UrlRepository urlRepository;

   private static Validator validator;

   static {
      ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
      validator = validatorFactory.getValidator();
   }

   public List<String> validateShortUrl(ShortUrl shortUrl) {
      List<String> violations = new ArrayList<String>();
      Set<ConstraintViolation<ShortUrl>> constraintViolations = validator.validate(shortUrl);
      for(ConstraintViolation constraintViolation : constraintViolations) {
         violations.add(constraintViolation.getMessage());
      }
      return violations;
   }

   public boolean saveUrl(ShortUrl shortUrl) {
      // FIXME handle exceptions
      ShortUrl savedShortUrl = urlRepository.save(shortUrl);
      return true;
   }

   public Iterable<ShortUrl> findAll() {
      return urlRepository.findAll();
   }

   public String expandShortUrl(String code) {
      ShortUrl shortUrl = urlRepository.findByShortUrl(code);
      return (shortUrl != null) ? shortUrl.getFullUrl() : "";
   }

}
