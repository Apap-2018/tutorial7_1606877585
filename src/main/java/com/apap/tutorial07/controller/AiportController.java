package com.apap.tutorial07.controller;

import com.apap.tutorial07.rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/airport")
public class AiportController {
    @Autowired
    RestTemplate restAirport;

    @Bean
    public RestTemplate restAirport(){
        return new RestTemplate();
    }

    @GetMapping(value = "/{cityOnlyInIndonesia}")
    public String getAirportInAcityInIndonesia(@PathVariable(name = "cityOnlyInIndonesia")String city){
        String url = Setting.airportUrl + city;
        return restAirport.getForEntity(url,String.class).getBody();
    }
}
