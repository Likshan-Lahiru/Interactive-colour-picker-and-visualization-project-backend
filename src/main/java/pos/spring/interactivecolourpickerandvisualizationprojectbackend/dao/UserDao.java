package pos.spring.interactivecolourpickerandvisualizationprojectbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.entity.impl.UserEntity;

import java.util.Optional;

public interface UserDao extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByEmailAndPassword(String email, String password);
}

