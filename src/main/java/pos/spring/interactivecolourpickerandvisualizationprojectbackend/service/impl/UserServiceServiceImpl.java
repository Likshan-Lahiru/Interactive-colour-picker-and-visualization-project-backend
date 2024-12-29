package pos.spring.interactivecolourpickerandvisualizationprojectbackend.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.dto.impl.UserDto;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.dto.status.Status;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.service.UserService;

import java.util.List;


@Service
@Transactional
public class UserServiceServiceImpl implements UserService {
    @Override
    public List<UserDto> getUserList() {
        return List.of();
    }

    @Override
    public Status getUserById(String userId) {
        return null;
    }

    @Override
    public void updateUser(String userId, UserDto userDto) {

    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public String generateUserID() {
        return "";
    }
}
