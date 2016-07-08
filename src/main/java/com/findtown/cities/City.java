package com.findtown.cities;

public class City {
    private String province ;
    private String[] providers;
    private String cityName;
    private String cityId;

    public City(String province, String[] providers, String cityName, String cityId) {
        this.province = province;
        this.providers = providers;
        this.cityName = cityName;
        this.cityId = cityId;
    }
    public String getProvince() {
        return province;
    }
    public String[] getProviders() {
        return providers;
    }
    public String getCityName() {
        return cityName;
    }
    public String getCityId() {
        return cityId;
    }
}
