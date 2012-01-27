import domain.ShortUrl;

public class UrlExpandAction {
   private UrlShortenerService urlShortenerService;
   private String fullUrl;

   public void setUrlShortenerService(UrlShortenerService urlShortenerService) {
      this.urlShortenerService = urlShortenerService;
   }

   public String getFullUrl() {
      return this.fullUrl;
   }

   public void setFullUrl(String fullUrl) {
      this.fullUrl = fullUrl;
   }

   public String execute() {
      // FIXME hardcoded this for now
      String shortUrl = "search";
      this.fullUrl = urlShortenerService.expandShortUrl(shortUrl);
      return "SUCCESS";
   }
}
