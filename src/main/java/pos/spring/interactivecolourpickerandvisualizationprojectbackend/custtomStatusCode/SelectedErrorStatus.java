package pos.spring.interactivecolourpickerandvisualizationprojectbackend.custtomStatusCode;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.dto.status.Status;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedErrorStatus implements Status {

    private int errorCode;
    private String errorMessage;

}
