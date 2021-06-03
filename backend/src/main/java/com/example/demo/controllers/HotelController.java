package com.example.demo.controllers;

import com.example.demo.entities.Hotel;
import com.example.demo.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
//Kommenterad och färdig!! :D

@RestController
@RequestMapping("/rest")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    //Hämtar ALLA hotel
    @GetMapping("/getAllHotels/{fromDate}/{toDate}/{size}/{room}")
    public List<Hotel> getAll(@PathVariable String fromDate, @PathVariable String toDate, @PathVariable double size, @PathVariable long room){
        return hotelService.getAll(fromDate, toDate, size, room); }

    //Hämtar hotel per stad på HotelID
    @GetMapping("/getHotelForCity/{id}/{fromDate}/{toDate}/{size}/{room}")
    public List<Hotel> getByCountry(@PathVariable Long id, @PathVariable String fromDate,@PathVariable String toDate,@PathVariable double size, @PathVariable long room){
        return hotelService.getByCity(id, fromDate, toDate, size, room); }

    //Hämtar hotel på HotelID
    @GetMapping("/hotelInfo/{id}")
    public List<Map> getHotelInfoById(@PathVariable Long id){return hotelService.hotelInfoById(id);}

    //Hämtar temperatur + cityName + countryName på HotelID
    @GetMapping("/hotelCityTemparatureByHotelId/{id}")
    public List<Map> getTemperatureCountryCityByHotelId(@PathVariable Long id){return hotelService.searchTemperatureHotelById(id);}

    //Hämtar attractions på HotelID
    @GetMapping("/hotelAttraction/{id}")
    public List<Map> getAttractionById(@PathVariable Long id){return hotelService.attractionById(id);}

    //Hämtar Comforts på HotelID
    @GetMapping("/hotelComforts/{id}")
    public List<Map> getComfortsById(@PathVariable Long id){return hotelService.comfortsById(id);}

    //sökfunktion på inputfras antingen hotel/country/city
    @GetMapping("hotelSearch/{phrase}/{fromDate}/{toDate}/{size}/{room}")
    public List<Hotel> getByName(@PathVariable String phrase, @PathVariable String fromDate, @PathVariable String toDate, @PathVariable double size, @PathVariable long room){
        return hotelService.getByPhrase(phrase, fromDate, toDate, size, room); }

    @GetMapping("tempSearch/{fromDate}/{toDate}/{size}/{room}/{temp}/{range}")
    public List<Hotel> getByTemp(@PathVariable String fromDate, @PathVariable String toDate, @PathVariable double size, @PathVariable long room, @PathVariable int temp,@PathVariable int range){
        return hotelService.getByTemp(fromDate, toDate, size, room, temp, range);}
}
