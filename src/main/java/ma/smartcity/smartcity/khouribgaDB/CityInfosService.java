package ma.smartcity.smartcity.khouribgaDB;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CityInfosService {

    private final CityInfosRepository cityInfosRepository;

    public List<CityInfos> getAllCityInfos(){
        System.out.println(cityInfosRepository.findAll());
        return cityInfosRepository.findAll();
    }

    public List<CityInfos> getCityInfoByCategory(String category){
        return cityInfosRepository.findCityInfosByCategory(category);
    }
}
