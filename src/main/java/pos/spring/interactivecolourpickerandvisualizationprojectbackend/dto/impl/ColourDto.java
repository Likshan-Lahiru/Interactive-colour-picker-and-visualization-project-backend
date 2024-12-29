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
public class ColourDto implements Status {
    private String id;
    private String image;
    private String color_C;
    private String color_M;
    private String color_Y;
    private String color_K;
    private float C_cost;
    private float M_cost;
    private float Y_cost;
    private float K_cost;
    private float fullCost;
    private String resolution;

}
