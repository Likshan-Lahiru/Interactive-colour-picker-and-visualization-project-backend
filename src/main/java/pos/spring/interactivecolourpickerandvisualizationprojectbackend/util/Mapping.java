package pos.spring.interactivecolourpickerandvisualizationprojectbackend.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.dto.impl.ColourDto;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.entity.impl.ColourEntity;

@Component
public class Mapping {

    @Autowired
    private ModelMapper modelMapper;

    public ColourEntity toColourEntity(ColourDto colourDto) {
        return modelMapper.map(colourDto, ColourEntity.class);
    }
}
