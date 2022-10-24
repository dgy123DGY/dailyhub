package com.markerhub.service;

import com.markerhub.base.dto.CollectDto;
import com.markerhub.base.dto.DatelineDto;
import com.markerhub.entity.Collect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CollectService {

	List<DatelineDto> getDatelineByUserId(long userId);

	Page<CollectDto> findUserCollects(long userId, String dateline, Pageable pageable);

	Collect findById(long id);

	void deleteById(long id);

	void save(Collect collect);

	Page<CollectDto> findSquareCollects(Pageable pageable);
}
