package com.user.model.mapper;

import com.user.model.dto.UserDto;
import com.user.model.tables.User;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userDtoToUser(User user);
}
