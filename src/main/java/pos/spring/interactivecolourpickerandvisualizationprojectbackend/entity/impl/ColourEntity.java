package pos.spring.interactivecolourpickerandvisualizationprojectbackend.entity.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.entity.SuperEntity;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "colourTable")
public class ColourEntity implements SuperEntity {

    @Id
    private String id;

    @Column(columnDefinition = "LONGTEXT")
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

    private Date created_at;

    @ManyToOne
    @JoinColumn(name = "userEntity", nullable = false)
    private UserEntity userEntity;

    @PrePersist
    public void prePersist() {
        if (created_at == null) {
            created_at = new Date();
        }
    }
}
