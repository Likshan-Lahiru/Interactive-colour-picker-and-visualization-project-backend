package pos.spring.interactivecolourpickerandvisualizationprojectbackend.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.custtomStatusCode.SelectedErrorStatus;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.dao.UserDao;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.dto.impl.UserDto;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.dto.status.Status;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.entity.impl.ColourEntity;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.entity.impl.UserEntity;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.exception.DataPersistException;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.exception.ItemNotFoundException;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.service.UserService;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.util.Mapping;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserServiceServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void signUp(UserDto userDto) {
        if ( userDao.save(mapping.toUserEntity(userDto)) == null) {
            throw new DataPersistException();
        }
    }
    @Override
    public boolean signIn(String email, String password) {
        return userDao.existsByEmailAndPassword(email, password);
    }


    @Override
    public List<UserDto> getUserList() {
        return mapping.asUserDtoList(userDao.findAll());
    }

    @Override
    public Status getUserById(String userId) {
        System.out.println("service layer user id"+userId);
        if (userDao.existsById(userId)) {
            UserEntity userEntity = userDao.getReferenceById(userId);
            return mapping.toUserDto(userEntity);
        }else {
            return new SelectedErrorStatus(2,"User not found");
        }
    }

    @Override
    public void updateUser(String userId, UserDto userDto) {
        Optional<UserEntity> byId = userDao.findById(userId);
        if (byId.isPresent()) {
            byId.get().setId(userDto.getId());
            byId.get().setUsername(userDto.getUsername());
            byId.get().setPassword(userDto.getPassword());
            byId.get().setEmail(userDto.getEmail());
            byId.get().setFirstName(userDto.getFirstName());
            byId.get().setLastName(userDto.getLastName());
            byId.get().setCompanyName(userDto.getCompanyName());
            byId.get().setPhoneNumber(userDto.getPhoneNumber());
            byId.get().setUserProfileImage(userDto.getUserProfileImage());


        }
    }

    @Override
    public void deleteUser(String userId) {
        Optional<UserEntity> existedUser = userDao.findById(userId);
        if(!existedUser.isPresent()){
            throw new ItemNotFoundException("User with id " + userId + " not found");
        }else {
            userDao.deleteById(userId);
        }

    }

    @Override
    public String generateUserID() {
        return "";
    }
}
