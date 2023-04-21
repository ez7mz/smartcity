package ma.smartcity.smartcity.khouribgaDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityInfosRepository extends JpaRepository<CityInfos, Long> {

}
