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
@Entity
public class CityInfos {
    @Id
    private Long id;
    private String name;
    private String location;
    private String url;
    private String mail;
    private String phone;
    private String description;
    private String category;
    @Column(length = 10000)
    private String image;
}
