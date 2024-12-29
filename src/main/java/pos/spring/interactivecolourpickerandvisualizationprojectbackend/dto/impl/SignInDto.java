package pos.spring.interactivecolourpickerandvisualizationprojectbackend.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.dto.status.Status;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SignInDto implements Status {
    private String email;
    private String password;
}
