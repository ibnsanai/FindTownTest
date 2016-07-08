package com.findtown.controller;

import com.findtown.cities.City;
import com.findtown.cities.interfaces.IListCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("singleton")
public class MainPageController {

    @Autowired
    private IListCityService cityService;

    private String updMessage = "Loading data from a source, please wait. Refresh the page in a few minutes..." ;
    private boolean okFirstStart = true ;
    private String timeUpd = getCurrentTimeStamp();

    private static final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy   kk:mm:ss");
    private static String getCurrentTimeStamp() {
        java.util.Date today = new java.util.Date();
        return dateFormat.format(today.getTime());
    }

    @RequestMapping(value = "/mainpage", method = RequestMethod.GET)
    public String mainPage(@RequestParam(value = "update", required=false) final String name,
                           Model model){

        if (cityService.getCityList().size() > 0) {
            if (okFirstStart) {
                updMessage = "List updated when the SERVER start " + timeUpd;
            }
            else {
                updMessage = "List updated HANDLER " + timeUpd;
            }
        }

        model.addAttribute("updMessage", updMessage);
        model.addAttribute("findCities", cityService.getCityList());

        if (name != null && name.equals("update")) {
            okFirstStart = false ;
            cityService.updCityList();
            updMessage = "Data is updated from source, please wait.  Refresh the page in a few minutes..." ;
            timeUpd = getCurrentTimeStamp();
            return "redirect:mainpage";
        } else return "main";
    }

    private List<City> findAllNeedCities(String cont){
        List<City> findCities = new ArrayList<>();
        for(City city : cityService.getCityList()){
            if (city.getCityName().toUpperCase().contains(cont.toUpperCase())){
                findCities.add(city);
            }
        }
        return ( findCities.size()>0 ? findCities : null);
    }

    @RequestMapping(value = "/respage", method = RequestMethod.GET)
    public String printCity(Model model,HttpServletRequest request) {
        List<City> findCities = findAllNeedCities(request.getParameter("cityname"));
        String findMessage ;
        if (findCities != null) {
            findMessage = "List of cities for your request \"" + request.getParameter("cityname")+ "\"   ";
            model.addAttribute("findMessage", findMessage);
            model.addAttribute("findCities", findCities);
        }else{
            findMessage = "Sorry, on request \"" + request.getParameter("cityname")+ "\" no search results   ";
            model.addAttribute("findMessage", findMessage);
        }
        return "result";
    }
}
