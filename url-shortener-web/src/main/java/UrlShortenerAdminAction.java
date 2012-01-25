import java.lang.Iterable;
import domain.ShortUrl;

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
