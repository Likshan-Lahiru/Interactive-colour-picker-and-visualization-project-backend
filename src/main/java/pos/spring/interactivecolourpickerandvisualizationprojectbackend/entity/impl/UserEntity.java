package pos.spring.interactivecolourpickerandvisualizationprojectbackend.entity.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pos.spring.interactivecolourpickerandvisualizationprojectbackend.entity.SuperEntity;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "userTable")
public class UserEntity implements SuperEntity {
    @Id
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String companyName;
    @Column(columnDefinition = "LONGTEXT")
    private String userProfileImage;
    @OneToMany(mappedBy = "userEntity")
    private List<ColourEntity> colourEntities;
}
