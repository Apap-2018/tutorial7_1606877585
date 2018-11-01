package com.apap.tutorial07.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.apap.tutorial07.model.FlightModel;
import com.apap.tutorial07.model.PilotModel;
import com.apap.tutorial07.service.FlightService;
import com.apap.tutorial07.service.PilotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * FlightController
 */
@RestController
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    private FlightService flightService;
    
    @Autowired
    private PilotService pilotService;



   @PostMapping(value = "/add")
   public FlightModel addFlight(@RequestBody FlightModel flightModel){
       return flightService.addFlight(flightModel);
   }

   @PutMapping("/update/{flightId}")
   public String updateFlight(@PathVariable("flightId")long id,
                              @RequestParam(name = "destination",required = false)String destination,
                              @RequestParam(name = "origin",required = false)String origin,
                              @RequestParam(name = "time",required = false)String date)
   throws Exception{
        FlightModel flightModel = flightService.getFLightDetailByFlightId(id).get();
        if (flightModel.equals(null))
            return "Couldn't find your flight";
        if (destination!=null)
            flightModel.setDestination(destination);
        if (origin!=null)
            flightModel.setOrigin(origin);
        if (date!=null) {
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date theDate = sdf1.parse(date);
            java.sql.Date sqlDate = new java.sql.Date(theDate.getTime());
            flightModel.setTime(sqlDate);
        }
        flightService.addFlight(flightModel);
        return "flight update success";
   }

   @GetMapping("/view/{flightNumber}")
   public FlightModel getFLight(@PathVariable("flightNumber")String flightNumber){
       return flightService.getFlightDetailByFlightNumber(flightNumber).get();
   }
   @GetMapping("/all")
   public List<FlightModel> getAllFlight(){
        List<FlightModel> flightAll = flightService.getAll().get();
        return flightAll;
   }

   @DeleteMapping("{flightId}")
   public String deleteFlight(@PathVariable(name = "flightId")long flightId){
       FlightModel flightModel = flightService.getFLightDetailByFlightId(flightId).get();
       flightService.deleteByFlightNumber(flightModel.getFlightNumber());
       return "flight has been deleted";
   }




}