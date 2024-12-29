package pos.spring.interactivecolourpickerandvisualizationprojectbackend.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.dto.status.Status;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class UserDto implements Status {
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String companyName;
    private String userProfileImage;
    private List<ColourDto> colourEntities;
}
