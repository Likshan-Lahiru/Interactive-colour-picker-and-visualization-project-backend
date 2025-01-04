package pos.spring.interactivecolourpickerandvisualizationprojectbackend.service;

import pos.spring.interactivecolourpickerandvisualizationprojectbackend.dto.impl.ColourDto;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.dto.status.Status;

import java.util.List;
import java.util.Map;

public interface ColourService  {
    void save(ColourDto colourDto);
    List<ColourDto> getColourList();
    Status getColourById(String colourId);
    List<ColourDto> getAllColoursByUserId(String userId);
    void updateColour(String colourId, ColourDto colourDto);
    void deleteColour(String colourId);
    String generateColourID();
    Map<String, Object> getUserStatistics(String userId);


}
