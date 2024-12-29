package pos.spring.interactivecolourpickerandvisualizationprojectbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.dto.impl.ColourDto;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.dto.impl.Cost;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.exception.DataPersistException;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.service.ColourService;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.util.ImageConverter;

import java.io.IOException;

@RestController
@RequestMapping("api/colour")
public class ColourController {

    @Autowired
    private ColourService colourService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveColour(
            @RequestPart("id") String id,
            @RequestPart("color_C") String color_C,
            @RequestPart("color_M") String color_M,
            @RequestPart("color_Y") String color_Y,
            @RequestPart("color_K") String color_K,
            @RequestPart("image") MultipartFile image,
            @RequestPart("resolution") String resolution,
            @RequestPart("userEntity") String userEntity
    ) {
        try {
            Cost cost = costCalculate(color_C,color_M,color_Y,color_K);

            colourService.save(assignValue(id,image,color_C,color_M,color_Y,color_K,cost,resolution,userEntity));

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ColourDto assignValue(String id, MultipartFile image, String colorC, String colorM, String colorY, String colorK, Cost cost, String resolution,String userEntity) throws IOException {
        ColourDto colourDto = new ColourDto();
        colourDto.setId(id);
        colourDto.setImage(ImageConverter.convertImage(image));
        colourDto.setColor_C(String.valueOf(colorC));
        colourDto.setColor_M(String.valueOf(colorM));
        colourDto.setColor_Y(String.valueOf(colorY));
        colourDto.setColor_K(String.valueOf(colorK));
        colourDto.setC_cost(cost.getC());
        colourDto.setM_cost(cost.getM());
        colourDto.setY_cost(cost.getY());
        colourDto.setK_cost(cost.getK());
        colourDto.setFullCost(cost.getTotal());
        colourDto.setResolution(resolution);
        colourDto.setUserEntity(userEntity);
        return colourDto;
    }

    private Cost costCalculate(String colorC, String colorM, String colorY, String colorK) {

        float C_cost_per_unit = 0.5f;
        float M_cost_per_unit = 0.4f;
        float Y_cost_per_unit = 0.3f;
        float K_cost_per_unit = 0.6f;

        float C_cost = Float.parseFloat(colorC) * C_cost_per_unit;
        float M_cost = Float.parseFloat(colorM) * M_cost_per_unit;
        float Y_cost = Float.parseFloat(colorY) * Y_cost_per_unit;
        float K_cost = Float.parseFloat(colorK) * K_cost_per_unit;
        float fullCost = C_cost + M_cost + Y_cost + K_cost;

        return new Cost(C_cost, M_cost, Y_cost, K_cost, fullCost);

    }


}
