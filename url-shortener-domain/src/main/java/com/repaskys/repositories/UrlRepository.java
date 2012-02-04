package com.repaskys.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.repaskys.domain.ShortUrl;

public interface UrlRepository extends PagingAndSortingRepository<ShortUrl, Long> {
   ShortUrl findByShortUrl(String shortUrl);
}
