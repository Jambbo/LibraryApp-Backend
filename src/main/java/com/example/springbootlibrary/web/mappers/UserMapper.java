package com.example.springbootlibrary.web.mappers;

import com.example.springbootlibrary.model.user.User;
import com.example.springbootlibrary.web.dto.user.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, UserDTO> {

//    @Override
//    default User toEntity(UserDTO userDTO) {
//        return User.builder()
//                .name(userDTO.getName())
//                .username(userDTO.getUsername())
//                .password(userDTO.getPassword())
//                .passwordConfirmation(userDTO.getPasswordConfirmation())
//                .build();
//    }
//
//    default UserDTO toDto(User user) {
//        return UserDTO.builder()
//                .build();
//    }

}
