package com.markerhub.service.impl;

import com.markerhub.base.dto.CollectDto;
import com.markerhub.base.dto.UserDto;
import com.markerhub.base.lang.Consts;
import com.markerhub.mapstruct.CollectDocMapper;
import com.markerhub.search.CollectDoc;
import com.markerhub.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	HttpSession httpSession;

	@Autowired
	ElasticsearchRestTemplate elasticsearchRestTemplate;

	@Autowired
	CollectDocMapper collectDocMapper;

	@Override
	public Page<CollectDto> search(String keyword, Long userId, Pageable pageable) {

		Criteria criteria = new Criteria();
		if (userId != null && userId > 0) {
			criteria.and(new Criteria("userId").is(userId));
		}

		UserDto userDto = (UserDto) httpSession.getAttribute(Consts.CURRENT_USER);
		if (userDto != null && userId != null) {
			// 已登录用户
			boolean isOwn = userId.longValue() == userDto.getId().longValue();
			if (isOwn) {
				// 1、搜索自己的
				criteria.and(new Criteria("personal").in(0, 1));
			} else{
				// 2、搜索别人的
				criteria.and(new Criteria("personal").in(0));
			}
		} else {
			// 未登录用户
			criteria.and(new Criteria("personal").is(0));
		}

		criteria = criteria.and(new Criteria("title").matches(keyword)).or(new Criteria("note").matches(keyword));

		CriteriaQuery criteriaQuery = new CriteriaQuery(criteria).setPageable(pageable);

		SearchHits<CollectDoc> searchHits = elasticsearchRestTemplate.search(criteriaQuery, CollectDoc.class);

		List<CollectDoc> collectDocs = searchHits.get().map(e -> e.getContent()).collect(Collectors.toList());

		Page<CollectDoc> docPage = new PageImpl(collectDocs, pageable, searchHits.getTotalHits());

		return docPage.map(collectDocMapper::toDto);
	}

}
