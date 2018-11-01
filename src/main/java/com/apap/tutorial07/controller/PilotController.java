package com.apap.tutorial07.controller;

import com.apap.tutorial07.model.PilotModel;
import com.apap.tutorial07.rest.PilotDetail;
import com.apap.tutorial07.rest.Setting;
import com.apap.tutorial07.service.PilotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * PilotController
 */
@RestController
@RequestMapping("/pilot")
public class PilotController {
    @Autowired
    private PilotService pilotService;

    @Autowired
    RestTemplate rest;

    @Bean
    public RestTemplate rest(){
        return new RestTemplate();
    }

    @PostMapping(value = "/add")
    public PilotModel addPilotSubmit(@RequestBody PilotModel pilotModel){
        return pilotService.addPilot(pilotModel);
    }

    @GetMapping(value = "view/{licenseNumber}")
    public PilotModel pilotView(@PathVariable("licenseNumber")String licenseNumber){
        PilotModel pilotModel = pilotService.getPilotDetailByLicenseNumber(licenseNumber).get();
        return pilotModel;
    }

    @DeleteMapping(value = "/delete")
    public String deletePilot(@RequestParam("pilotId") long pilotId){
        PilotModel pilotModel = pilotService.getPilotDetailById(pilotId).get();
        pilotService.deletePilotByLicenseNumber(pilotModel.getLicenseNumber());
        return "success";
    }

    @PutMapping(value = "/update/{pilotId}")
    public String updatePilotSubmit(@PathVariable("pilotId")long pilotId,
                                    @RequestParam("name")String name,
                                    @RequestParam("flyHour")int flyHour){
        PilotModel pilotModel = pilotService.getPilotDetailById(pilotId).get();
        if (pilotModel.equals(null))
            return "Couldn't find your pilot";

        pilotModel.setName(name);
        pilotModel.setFlyHour(flyHour);
        pilotService.addPilot(pilotModel);
        return "update";
    }



    @GetMapping(value = "/status/{licenseNumber}")
    public String getStatus(@PathVariable("licenseNumber")String licenseNumber)throws Exception{
        String path = Setting.pilotUrl + "/pilot?licenseNumber="+licenseNumber;
        return rest.getForEntity(path,String.class).getBody();
    }

    @GetMapping(value = "/full/{licenseNumber}")
    public PilotDetail postStatus(@PathVariable("licenseNumber")String licenseNumber)throws Exception{
        String path = Setting.pilotUrl + "/pilot";
        PilotModel pilotModel = pilotService.getPilotDetailByLicenseNumber(licenseNumber).get();
        System.err.println(pilotModel.getName());
        PilotDetail detail = rest.postForObject(path,pilotModel,PilotDetail.class);
        return detail;
    }




}