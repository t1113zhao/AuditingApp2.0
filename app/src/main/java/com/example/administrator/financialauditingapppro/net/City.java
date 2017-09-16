package com.example.administrator.financialauditingapppro.net;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 6/7/2017.
 */

public class City implements Comparable<City>{

    @SerializedName("cityid")
    public String cityid;

    @SerializedName("cityname")
    public String cityname;

    @SerializedName("pinyin")
    public String pinyin;

    public City() {
    }

    public City(String cityname) {
        this.cityname = cityname;
    }

    public City(String cityid, String cityname, String pinyin) {
        this.cityid = cityid;
        this.cityname = cityname;
        this.pinyin = pinyin;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        if (cityname == null) {
            return city.cityname == null;
        }
        return cityname.equals(city.cityname);
    }

    public int code;

    @Override
    public int compareTo(City another) {
        if (TextUtils.isEmpty(another.pinyin)) {
            return 0;
        }
        if (TextUtils.isEmpty(pinyin)) {
            return 0;
        }
        char[] letter = pinyin.toLowerCase().toCharArray();
        char[] _letter = another.pinyin.toLowerCase().toCharArray();
        int count = Math.max(letter.length, _letter.length);
        int i = 0;
        while (i < count) {
            if (i >= letter.length) return -1;
            if (i >= _letter.length) return 1;
            if (letter[i] - _letter[i] != 0) return letter[i] - _letter[i];
            i++;
        }
        return 0;
    }
}
