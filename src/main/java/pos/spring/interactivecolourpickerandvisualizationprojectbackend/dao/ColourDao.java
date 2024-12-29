package pos.spring.interactivecolourpickerandvisualizationprojectbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.entity.impl.ColourEntity;

import java.util.List;

public interface ColourDao extends JpaRepository<ColourEntity,String> {
    @Query("SELECT c FROM ColourEntity c WHERE c.userEntity.id = :userId")
    List<ColourEntity> findAllByUserId(@Param("userId") String userId);
}
