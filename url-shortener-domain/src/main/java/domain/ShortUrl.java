package domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class ShortUrl implements Serializable {
   @Id
   @GeneratedValue(strategy = AUTO)
   private Long id;

   @NotNull
   private String fullUrl;

   @NotNull
   @Size(min = 2, max = 14)
   private String shortUrl;

   public String getFullUrl() {
      return this.fullUrl;
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
