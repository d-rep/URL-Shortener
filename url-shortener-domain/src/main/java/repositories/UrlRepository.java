package repositories;

import org.springframework.data.repository.CrudRepository;
import domain.ShortUrl;

public interface UrlRepository extends CrudRepository<ShortUrl, Long> {
}
