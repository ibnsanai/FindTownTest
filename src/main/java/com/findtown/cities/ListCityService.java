package com.findtown.cities;

import com.findtown.cities.interfaces.IListCityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListCityService implements IListCityService {

    private List<City> cityList = new ArrayList<>();

    public ListCityService() {
        cityList = getAllKnownCities();
    }

    @Override
    public List<City> getCityList() {
        return cityList;
    }

    @Override
    public void updCityList(){
        cityList.clear();
        cityList =  getAllKnownCities();
    }

    private List<City> getAllKnownCities(){
        GetJsonThread getJsonThread = new GetJsonThread();
        getJsonThread.start();
        return getJsonThread.getNewList() ;
    }

}
