package com.markerhub.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CollectDocRepository extends ElasticsearchRepository<CollectDoc, Long> {
}
