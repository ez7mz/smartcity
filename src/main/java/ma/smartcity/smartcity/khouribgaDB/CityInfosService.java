package ma.smartcity.smartcity.khouribgaDB;

import lombok.AllArgsConstructor;
import ma.smartcity.smartcity.appuser.AppUser;
import ma.smartcity.smartcity.appuser.AppUserRole;
import ma.smartcity.smartcity.appuser.AppUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@AllArgsConstructor
public class CityInfosService {

    private final CityInfosRepository cityInfosRepository;
    private final AppUserService appUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    public List<CityInfos> init() {
        System.out.println("Filling the Database with the goodies ...");
//        AppUser admin = new AppUser("Admin", "User", "root@root.com", bCryptPasswordEncoder.encode("root"), AppUserRole.ADMIN);
//        appUserService.saveUser(admin);
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

    public Long getTotalItem(){
        return cityInfosRepository.count();
    }

    public void saveItem(CityInfos item){
        cityInfosRepository.save(item);
    }

    public Long countByCategory(String category){
        return cityInfosRepository.countCityInfosByCategory(category);
    }

}
