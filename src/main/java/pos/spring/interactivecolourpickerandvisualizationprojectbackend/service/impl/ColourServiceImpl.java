package pos.spring.interactivecolourpickerandvisualizationprojectbackend.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.dto.impl.ColourDto;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.dto.status.Status;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.service.ColourService;

import java.util.List;


@Service
@Transactional
public class ColourServiceImpl implements ColourService {

    @Override
    public List<ColourDto> getColourList() {
        return List.of();
    }

    @Override
    public Status getColourById(String colourId) {
        return null;
    }

    @Override
    public void updateColour(String colourId, ColourDto colourDto) {

    }

    @Override
    public void deleteColour(String colourId) {

    }

    @Override
    public String generateColourID() {
        return "";
    }
}
