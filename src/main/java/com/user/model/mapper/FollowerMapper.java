package com.user.model.mapper;

import com.user.model.dto.FollowerDto;
import com.user.model.tables.Followers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface FollowerMapper {

    @Mappings({
            @Mapping(source = "from", target = "from.id"),
            @Mapping(source = "to", target = "to.id")
    })
    Followers followerDtoToFollower(FollowerDto followerDto);

    @Mappings({
            @Mapping(source = "from.id", target = "from"),
            @Mapping(source = "to.id", target = "to")
    })
    FollowerDto followerToFollowerDto(Followers follower);

}
