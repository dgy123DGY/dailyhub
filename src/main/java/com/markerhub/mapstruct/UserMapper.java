package com.markerhub.mapstruct;

import com.markerhub.base.dto.UserDto;
import com.markerhub.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

	UserDto toDto(User user);

}
