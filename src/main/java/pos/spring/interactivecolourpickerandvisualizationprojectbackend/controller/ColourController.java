package pos.spring.interactivecolourpickerandvisualizationprojectbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.dto.impl.ColourDto;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.dto.impl.Cost;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.dto.status.Status;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.exception.DataPersistException;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.exception.ItemNotFoundException;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.service.ColourService;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.util.ImageConverter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
            Cost cost = costCalculate(color_C, color_M, color_Y, color_K, resolution);

            colourService.save(assignValue(id, image, color_C, color_M, color_Y, color_K, cost, resolution, userEntity));

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ColourDto> getColourList(){
        return colourService.getColourList();
    }

    @GetMapping(value = "/{colourCode}")
    public Status getColourById(@PathVariable("colourCode") String colourCode){
        System.out.println("get Colour id"+ colourCode);
        return colourService.getColourById(colourCode);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ColourDto>> getColoursByUserId(@PathVariable String userId) {
        try {
            List<ColourDto> colours = colourService.getAllColoursByUserId(userId);
            return colours.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(colours);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{ColourCode}")
    public ResponseEntity<Object> deleteColour(@PathVariable("ColourCode") String colourCode){
        try {
            colourService.deleteColour(colourCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (ItemNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
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

    private Cost costCalculate(String colorC, String colorM, String colorY, String colorK, String resolution) {
        float C_cost_per_unit = 0.5f;
        float M_cost_per_unit = 0.4f;
        float Y_cost_per_unit = 0.3f;
        float K_cost_per_unit = 0.6f;

        float C_cost = Float.parseFloat(colorC) * C_cost_per_unit;
        float M_cost = Float.parseFloat(colorM) * M_cost_per_unit;
        float Y_cost = Float.parseFloat(colorY) * Y_cost_per_unit;
        float K_cost = Float.parseFloat(colorK) * K_cost_per_unit;
        float baseCost = C_cost + M_cost + Y_cost + K_cost;


        float resolutionMultiplier = calculateResolutionMultiplier(resolution);
        float finalCost = baseCost * resolutionMultiplier;

        return new Cost(C_cost, M_cost, Y_cost, K_cost, finalCost);
    }

    private float calculateResolutionMultiplier(String resolution) {
        try {

            String[] dimensions = resolution.toLowerCase().split("x");
            int width = Integer.parseInt(dimensions[0]);
            int height = Integer.parseInt(dimensions[1]);


            int totalPixels = width * height;


            if (totalPixels <= 921600) { // <= 720p (1280x720)
                return 1.0f; // No additional cost
            } else if (totalPixels <= 2073600) { // <= 1080p (1920x1080)
                return 1.2f; // 20% increment for 1080p
            } else if (totalPixels <= 8294400) { // <= 4K (3840x2160)
                return 1.5f; // 50% increment for 4K
            } else {
                return 2.0f; // 100% increment for higher than 4K
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 1.0f;
        }
    }

    @GetMapping("/genColourID")
    public String generateColourId(){
        return colourService.generateColourID();
    }

    @GetMapping("/statistics/{userId}")
    public ResponseEntity<Map<String, Object>> getUserStatistics(@PathVariable String userId) {
        Map<String, Object> statistics = colourService.getUserStatistics(userId);
        return ResponseEntity.ok(statistics);
    }


}
