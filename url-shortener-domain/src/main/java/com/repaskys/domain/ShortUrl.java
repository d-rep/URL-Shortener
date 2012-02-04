package com.repaskys.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class ShortUrl implements Serializable {
   private static final String HTTP_PREFIX = "http://";

   @Id
   @GeneratedValue(strategy = AUTO)
   private Long id;

   @NotNull(message = "Full URL {javax.validation.constraints.NotNull.message}")
   @Size(min = 1, max = 500, message="Full URL {javax.validation.constraints.Size.message}")
   private String fullUrl;

   @NotNull(message = "Short URL {javax.validation.constraints.NotNull.message}")
   @Size(min = 1, max = 20, message="Short URL {javax.validation.constraints.Size.message}")
   private String shortUrl;

   public String getFullUrl() {
      String full = this.fullUrl;
      if(! full.startsWith(HTTP_PREFIX)) {
         full = HTTP_PREFIX + full;
      }
      return full;
   }

   public void setFullUrl(String fullUrl) {
      this.fullUrl = fullUrl;
   }

   public String getShortUrl() {
      return this.shortUrl;
   }

   public void setShortUrl(String shortUrl) {
      this.shortUrl = shortUrl;
   }
}
