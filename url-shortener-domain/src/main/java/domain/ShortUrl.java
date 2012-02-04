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

   @NotNull(message = "Full URL {javax.validation.constraints.NotNull.message}")
   @Size(min = 1, max = 500, message="Full URL {javax.validation.constraints.Size.message}")
   private String fullUrl;

   @NotNull(message = "Short URL {javax.validation.constraints.NotNull.message}")
   @Size(min = 1, max = 20, message="Short URL {javax.validation.constraints.Size.message}")
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
