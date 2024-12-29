package pos.spring.interactivecolourpickerandvisualizationprojectbackend.service;

import pos.spring.interactivecolourpickerandvisualizationprojectbackend.dto.impl.UserDto;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.dto.status.Status;

import java.util.List;

public interface UserService {
    void signUp(UserDto userDto);
    List<UserDto> getUserList();
    Status getUserById(String userId);
    void updateUser(String userId, UserDto userDto);
    void deleteUser(String userId);
    String generateUserID();
    boolean signIn(String email, String password);
}
