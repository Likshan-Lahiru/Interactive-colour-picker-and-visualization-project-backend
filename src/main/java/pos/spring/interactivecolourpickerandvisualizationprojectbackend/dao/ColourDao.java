package pos.spring.interactivecolourpickerandvisualizationprojectbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.entity.impl.ColourEntity;

public interface ColourDao extends JpaRepository<ColourEntity,String> {
}
