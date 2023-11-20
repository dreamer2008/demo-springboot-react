package com.tom.demo.util;

import com.tom.demo.dto.UserDto;
import com.tom.demo.model.User;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface Mapper {

    Mapper INSTANCE = Mappers.getMapper(Mapper.class);

    User toModel(UserDto userDto);

    List<User> toModels(List<UserDto> list);

}
