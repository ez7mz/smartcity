package ma.smartcity.smartcity.khouribgaDB;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class CityInfos {
    @Id
    private Long id;
    private String name;
    @Column(name="location",columnDefinition="LONGTEXT")
    private String location;
    private String url;
    private String mail;
    private String phone;
    @Column(name="description",columnDefinition="LONGTEXT")
    private String description;
    private String category;
    @Column(name="image",columnDefinition="LONGTEXT")
    private String image;
}
