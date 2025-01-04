package pos.spring.interactivecolourpickerandvisualizationprojectbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.entity.impl.ColourEntity;

import java.util.Date;
import java.util.List;

public interface ColourDao extends JpaRepository<ColourEntity, String> {

    @Query("SELECT c FROM ColourEntity c WHERE c.userEntity.id = :userId")
    List<ColourEntity> findAllByUserId(@Param("userId") String userId);

    @Query("SELECT SUM(c.fullCost) FROM ColourEntity c WHERE c.userEntity.id = :userId")
    Double findTotalCostByUserId(@Param("userId") String userId);

    @Query("SELECT COUNT(c.id) FROM ColourEntity c WHERE c.userEntity.id = :userId")
    Long findTotalImageCountByUserId(@Param("userId") String userId);

    @Query("SELECT COUNT(c.id) * 1.0 / 7 FROM ColourEntity c " +
            "WHERE c.userEntity.id = :userId AND c.created_at >= :startDate")
    Double findWeeklyImageAverage(@Param("userId") String userId, @Param("startDate") Date startDate);
}
