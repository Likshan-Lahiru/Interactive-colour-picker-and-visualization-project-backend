package pos.spring.interactivecolourpickerandvisualizationprojectbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.dto.impl.ColourDto;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.dto.impl.UserDto;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.dto.status.Status;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.exception.DataPersistException;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.exception.ItemNotFoundException;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.service.UserService;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.util.ImageConverter;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveColour(
            @RequestPart("id") String id,
            @RequestPart("username") String username,
            @RequestPart("password") String password,
            @RequestPart("firstName") String firstName,
            @RequestPart("lastName") String lastName,
            @RequestPart("email") String email,
            @RequestPart("phoneNumber") String phoneNumber,
            @RequestPart("companyName") String companyName,
            @RequestPart("userProfileImage") MultipartFile userProfileImage

    ) {
        try {
            userService.signUp(assignValue(id,username,password,firstName,lastName,email,phoneNumber,companyName,userProfileImage));

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{userCode}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateColour(
            @PathVariable("userCode") String userCode,
            @RequestPart("id") String id,
            @RequestPart("username") String username,
            @RequestPart("password") String password,
            @RequestPart("firstName") String firstName,
            @RequestPart("lastName") String lastName,
            @RequestPart("email") String email,
            @RequestPart("phoneNumber") String phoneNumber,
            @RequestPart("companyName") String companyName,
            @RequestPart("userProfileImage") MultipartFile userProfileImage

    ) {
        try {
            userService.updateUser(userCode,assignValue(id,username,password,firstName,lastName,email,phoneNumber,companyName,userProfileImage));

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
    public List<UserDto> getUserList(){
        return userService.getUserList();
    }

    @GetMapping(value = "/{userCode}")
    public Status getUserById(@PathVariable("userCode") String userCode){
        System.out.println("get Colour id"+ userCode);
        return userService.getUserById(userCode);
    }

    @DeleteMapping(value = "/{userCode}")
    public ResponseEntity<Object> deleteUser(@PathVariable("userCode") String userCode){
        try {
            userService.deleteUser(userCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (ItemNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    private UserDto assignValue(String id, String username, String password,String firstName, String lastName, String email, String phoneNumber, String companyName, MultipartFile userProfileImage) throws IOException {
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setUsername(username);
        userDto.setPassword(password);
        userDto.setFirstName(firstName);
        userDto.setLastName(lastName);
        userDto.setEmail(email);
        userDto.setPhoneNumber(phoneNumber);
        userDto.setCompanyName(companyName);
        userDto.setUserProfileImage(ImageConverter.convertImage(userProfileImage));
        return userDto;
    }


}
