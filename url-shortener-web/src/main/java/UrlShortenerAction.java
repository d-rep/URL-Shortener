import domain.ShortUrl;

public class UrlShortenerAction {
   private UrlShortenerService urlShortenerService;
   private ShortUrl shortUrl;

   public void setUrlShortenerService(UrlShortenerService urlShortenerService) {
      this.urlShortenerService = urlShortenerService;
   }

   public ShortUrl getShortUrl() {
      return shortUrl;
   }

   public void setShortUrl(ShortUrl shortUrl) {
      this.shortUrl = shortUrl;
   }

   public String execute() {
      urlShortenerService.saveUrl(shortUrl);
      return "SUCCESS";
   }
}
