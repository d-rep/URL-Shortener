package repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import domain.ShortUrl;

public interface UrlRepository extends PagingAndSortingRepository<ShortUrl, Long> {
   ShortUrl findByShortUrl(String shortUrl);
}
