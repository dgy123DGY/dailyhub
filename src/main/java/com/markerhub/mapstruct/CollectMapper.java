package com.markerhub.mapstruct;

import com.markerhub.base.dto.CollectDto;
import com.markerhub.entity.Collect;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CollectMapper {

	CollectDto toDto(Collect collect);

	List<CollectDto> toList(List<Collect> collects);

}
