package pos.spring.interactivecolourpickerandvisualizationprojectbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.exception.DataPersistException;

@RestController
@RequestMapping("api/v1/colour")
public class ColourController {

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCrop(
            @RequestPart("cropCode") String cropCode,
            @RequestPart ("cropCommonName") String cropCommonName,
            @RequestPart ("cropScientificName") String cropScientificName,
            @RequestPart ("cropCategory") String cropCategory,
            @RequestPart ("cropImage") MultipartFile cropImage,
            @RequestPart ("cropSeason") String cropSeason,
            @RequestPart ("fieldCode") String fieldCode,
            @RequestPart ("logCode") String logCode
    ){
        try {

            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
