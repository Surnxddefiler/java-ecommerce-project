package ecommerce.ecommerce_project.mappers;

import ecommerce.ecommerce_project.db.entities.UserEntity;
import ecommerce.ecommerce_project.userClass.UserRequest;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserEntity toEntity(UserRequest userRequest){
        return new UserEntity(null, userRequest.username(), userRequest.email(), null, 00.0);
    }
}
