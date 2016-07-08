package com.findtown.cities.interfaces;

import com.findtown.cities.City;

import java.util.List;

public interface IListCityService {
    List<City> getCityList();
    void updCityList();
}
