package ma.smartcity.smartcity.khouribgaDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityInfosRepository extends JpaRepository<CityInfos, Long> {
    public List<CityInfos> findCityInfosByCategory(String category);
}
