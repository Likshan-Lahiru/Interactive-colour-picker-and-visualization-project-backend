package pos.spring.interactivecolourpickerandvisualizationprojectbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.entity.impl.UserEntity;

public interface UserDao extends JpaRepository<UserEntity,String> {
}
