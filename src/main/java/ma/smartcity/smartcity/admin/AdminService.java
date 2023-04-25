package ma.smartcity.smartcity.admin;

import lombok.AllArgsConstructor;
import ma.smartcity.smartcity.khouribgaDB.CityInfos;
import ma.smartcity.smartcity.khouribgaDB.CityInfosService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
@AllArgsConstructor
public class AdminService {

    private final CityInfosService cityInfosService;
    public ModelAndView saveItem(CityInfos item){
        item.setId(cityInfosService.getTotalItem() + 1);
        if (item.getImage() == null | item.getImage().trim().isEmpty()){
            item.setImage("https://cdn.discordapp.com/attachments/953060662463049808/1099367149933445233/placeholder1.png");
        }
        cityInfosService.saveItem(item);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("item", item);
        modelAndView.setViewName("admin/itemSaved");
        return modelAndView;
    }
}
