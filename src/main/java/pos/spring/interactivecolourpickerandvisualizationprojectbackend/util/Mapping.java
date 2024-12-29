package pos.spring.interactivecolourpickerandvisualizationprojectbackend.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.dto.impl.ColourDto;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.dto.status.Status;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.entity.impl.ColourEntity;

import java.util.List;

@Component
public class Mapping {

    @Autowired
    private ModelMapper modelMapper;

    public ColourEntity toColourEntity(ColourDto colourDto) {
        return modelMapper.map(colourDto, ColourEntity.class);
    }

    public List<ColourDto> asColourDtoList(List<ColourEntity> all) {
        return modelMapper.map(all, new TypeToken<List<ColourDto>>() {}.getType());
    }

    public ColourDto toColourDto(ColourEntity colourEntity) {
        return modelMapper.map(colourEntity, ColourDto.class);
    }
}
