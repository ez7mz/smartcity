package ma.smartcity.smartcity.khouribgaDB;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@AllArgsConstructor
public class CityInfosService {

    private final CityInfosRepository cityInfosRepository;

    @PostConstruct
    public List<CityInfos> init() {
        System.out.println("Filling the Database with the goodies ...");
//        System.out.println(CityInfosReader.readCsv("src/main/resources/static/Assets/database/khouribgaDB.csv"));
        return cityInfosRepository.saveAll(CityInfosReader.readCsv("src/main/resources/static/Assets/database/khouribgaDB.csv"));
    }

    public List<CityInfos> getAllCityInfos(){
        System.out.println(cityInfosRepository.findAll());
        return cityInfosRepository.findAll();
    }

    public List<CityInfos> getCityInfoByCategory(String category){
        return cityInfosRepository.findCityInfosByCategory(category);
    }

}
