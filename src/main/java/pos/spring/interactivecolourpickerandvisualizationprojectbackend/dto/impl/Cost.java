package pos.spring.interactivecolourpickerandvisualizationprojectbackend.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Cost {
    private float C;
    private float M;
    private float Y;
    private float K;
    private float total;

}
