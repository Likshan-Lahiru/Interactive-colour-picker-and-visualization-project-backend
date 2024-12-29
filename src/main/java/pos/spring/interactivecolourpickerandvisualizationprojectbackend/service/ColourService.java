package pos.spring.interactivecolourpickerandvisualizationprojectbackend.service;

import pos.spring.interactivecolourpickerandvisualizationprojectbackend.dto.impl.ColourDto;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.dto.status.Status;

import java.util.List;

public interface ColourService  {
    void save(ColourDto colourDto);
    List<ColourDto> getColourList();
    Status getColourById(String colourId);
    void updateColour(String colourId, ColourDto colourDto);
    void deleteColour(String colourId);
    String generateColourID();


}
