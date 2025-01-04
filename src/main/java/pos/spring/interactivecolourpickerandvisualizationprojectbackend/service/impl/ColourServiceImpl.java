package pos.spring.interactivecolourpickerandvisualizationprojectbackend.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
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
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.exception.ItemNotFoundException;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.service.ColourService;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.util.Mapping;

import java.util.*;


@Service
@Transactional
public class ColourServiceImpl implements ColourService {
    @PersistenceContext
    private EntityManager entityManager;

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
            return new SelectedErrorStatus(2,"Colour not found");
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
        Optional<ColourEntity> existedColour = colourDao.findById(colourId);
        if(!existedColour.isPresent()){
            throw new ItemNotFoundException("Colour with id " + colourId + " not found");
        }else {
            colourDao.deleteById(colourId);
        }
    }

    @Override
    public String generateColourID() {
        TypedQuery<String> query = entityManager.createQuery(
                "SELECT c.id FROM ColourEntity c ORDER BY c.id DESC", String.class);
        query.setMaxResults(1);


        String lastCropId = query.getResultStream().findFirst().orElse(null);

        if (lastCropId != null) {

            int generatedCropId = Integer.parseInt(lastCropId.replace("C00-", "")) + 1;
            return String.format("C00-%03d", generatedCropId);
        } else {

            return "C00-001";
        }
    }

    @Override
    public Map<String, Object> getUserStatistics(String userId) {
        Map<String, Object> statistics = new HashMap<>();


        Double totalCost = colourDao.findTotalCostByUserId(userId);
        statistics.put("totalCost", totalCost != null ? totalCost : 0.0);


        Long totalImageCount = colourDao.findTotalImageCountByUserId(userId);
        statistics.put("totalImageCount", totalImageCount != null ? totalImageCount : 0L);


        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -7);
        Date lastWeek = calendar.getTime();

        Double weeklyAverage = colourDao.findWeeklyImageAverage(userId, lastWeek);
        statistics.put("weeklyAverage", weeklyAverage != null ? weeklyAverage : 0.0);

        return statistics;
    }
}
