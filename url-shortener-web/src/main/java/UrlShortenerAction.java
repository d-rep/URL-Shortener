public class UrlShortenerAction {
   private ShortUrl shortUrl;

   public ShortUrl getShortUrl() {
      return shortUrl;
   }

   public void setShortUrl(ShortUrl shortUrl) {
      this.shortUrl = shortUrl;
   }

   public String execute() {
      return "SUCCESS";
   }
}
