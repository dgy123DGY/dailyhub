package com.markerhub.repository;

import com.markerhub.entity.Collect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface CollectReopsitory extends JpaRepository<Collect, Long>, JpaSpecificationExecutor<Collect> {

	@Query(value="select distinct collected from m_collect where user_id = ? order by collected desc", nativeQuery = true)
	List<Date> getDatelineByUserId(long userId);

	Page<Collect> findAllByPersonal(Integer personal, Pageable pageable);

}
