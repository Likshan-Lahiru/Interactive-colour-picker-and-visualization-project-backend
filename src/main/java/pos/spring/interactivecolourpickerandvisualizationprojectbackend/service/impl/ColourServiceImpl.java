package pos.spring.interactivecolourpickerandvisualizationprojectbackend.service.impl;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.custtomStatusCode.SelectedErrorStatus;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.dao.ColourDao;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.dao.UserDao;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.dto.impl.ColourDto;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.dto.status.Status;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.entity.impl.ColourEntity;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.entity.impl.UserEntity;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.exception.DataPersistException;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.service.ColourService;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.util.Mapping;

import java.util.List;


@Service
@Transactional
public class ColourServiceImpl implements ColourService {
    @Autowired
    private ColourDao colourDao;

    @Autowired
    private Mapping mapping;

    @Autowired
    private UserDao userDao;


    @Override
    public void save(ColourDto colourDto) {
        UserEntity referenceById = userDao.getReferenceById(colourDto.getUserEntity());
        System.out.println("user enetity service layer"+colourDto.getUserEntity());
        ColourEntity colourEntity1 = mapping.toColourEntity(colourDto);
        colourEntity1.setUserEntity(referenceById);
        if ( colourDao.save(colourEntity1) == null) {
            throw new DataPersistException();
        }


    }

    @Override
    public List<ColourDto> getColourList() {
        return mapping.asColourDtoList(colourDao.findAll());
    }

    @Override
    public Status getColourById(String colourId) {
        if (colourDao.existsById(colourId)) {
            ColourEntity colourEntity = colourDao.getReferenceById(colourId);
            return mapping.toColourDto(colourEntity);
        }else {
            return new SelectedErrorStatus(2,"Crop not found");
        }
    }
    public List<ColourDto> getAllColoursByUserId(String userId) {
        List<ColourEntity> colourEntities = colourDao.findAllByUserId(userId);
        return mapping.asColourDtoList(colourEntities);
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
