package com.user.model.mapper;

import com.user.model.dto.UserReduced;
import com.user.model.tables.User;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserReduced userToUserDto(User user);

}
