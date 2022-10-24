package com.markerhub.service;

import com.markerhub.base.dto.CollectDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchService {

	Page<CollectDto> search(String keyword, Long userId, Pageable pageable);
}
