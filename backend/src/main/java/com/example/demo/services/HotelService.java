package com.example.demo.services;

import com.example.demo.entities.City;
import com.example.demo.entities.Hotel;
import com.example.demo.repositories.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//Kommenterad och Klar :D
@Service
public class HotelService {
    @Autowired
    private HotelRepo hotelRepo;

    /*
    tar emot en "phrase" att söka på,
    kollar hotelRepo om phrase inte är null.
    Om det är null returnerar vi en tom lista,
    om den inte är null så hämtar vi alla matchningar till phrase,
    loopar även ut hotel och sätter dit comforts och price som
    tillhör det hotellet.
     */
    public List<Hotel> getByPhrase(String phrase, String fromDate, String toDate, double size, long room) {
        List<Hotel> hotelList = new ArrayList<>();
        if(hotelRepo.findByName (phrase, fromDate, toDate, size, room) != null ){
            hotelList.addAll(hotelRepo.findByName(phrase, fromDate, toDate, size, room));
            for(Hotel hotel : hotelList){
                hotel.setComfortList(getComfortsForCard(hotel.getId()));
                hotel.setAttractionList(getAttractionsForCard(hotel.getCityId()));
                hotel.setPrice(getPrice(hotel.getId()));
                hotel.setPlaceName(getPlaceName(hotel.getId()));
            }
        }
        return hotelList;
    }
        /*
        hämtar ut en stad på ID som
        där vi sätter comfort och price som
        matchar ID:et
        och returnerar en lista
         */
    public List<Hotel> getByCity(Long id, String fromDate, String toDate, double size, long room) {
        List<Hotel> hotelList = hotelRepo.getByCityId(id, fromDate, toDate, size, room);
        for(Hotel hotel : hotelList){
            for(Map map : attractionById(hotel.getId())){
                hotel.setComfortList(getComfortsForCard(hotel.getId()));
                hotel.setAttractionList(getAttractionsForCard(hotel.getCityId()));
                hotel.setPrice(getPrice(hotel.getId()));
                hotel.setPlaceName(getPlaceName(hotel.getId()));
            }
        }
        return hotelList;
    }
    /*
    hämtar comforts
    på ett HotelID
     */
    private List<String> getComforts(Long id){
        return hotelRepo.comfortsPerHotel(id);
    }

    /*
    Hämtar ut allting ifrån Hotels tabellen
    baserat på HotelID vi skickar in
     */
    public List<Map> hotelInfoById(Long id) {
         List<Map> countryCity = hotelRepo.getInfoHotelById(id);
      return countryCity;
    }

    /*
    hämtar temperature + city + country
    baserat på HotelID
     */
    public List<Map> searchTemperatureHotelById(Long id) {
        List<Map> hotelInfo = hotelRepo.temperatureInfoByHotelId(id);
        return hotelInfo;
    }
    /*
    hämtar  attractions på HotelID
     */
    public List<Map> attractionById(Long id) {
        List<Map> attractions = hotelRepo.allAttractions(id);
        return attractions;
    }
    /*
    hämtar alla comforts på HotelID
     */
    public List<Map> comfortsById(Long id) {
        List<Map> allComfortsById = hotelRepo.hotelsComforts(id);
        return allComfortsById;
    }

    /*
   hämtar pris som matchar
   HotelID och sorterar
   minsta pris först
    */
    private int getPrice(Long id){
        return hotelRepo.cheapestPrice(id);
    }

    private List<String> getComfortsForCard(Long id){
        return hotelRepo.comfortsPerHotel(id);
    }

    private List<String> getAttractionsForCard(Long id){
        return hotelRepo.attractionsPerHotel(id);
    }

    //hämtar land och stad som en string
    private String getPlaceName(Long id){
        String placeName = hotelRepo.placeName(id);
        String[] newName = placeName.split(",");
        placeName = newName[0] + ", " + newName[1];
        return placeName;
    }

    public List<Hotel> getByTemp(String fromDate, String toDate, double size, long room, int temp, int range) {
        List<Hotel> hotelList = new ArrayList<>();
        if(hotelRepo.countryTemperature(fromDate, toDate, size, room, temp-range,temp+range) != null){
            hotelList.addAll(hotelRepo.countryTemperature(fromDate, toDate, size, room, temp-range,temp+range));
            for(Hotel hotel : hotelList){
                hotel.setComfortList(getComfortsForCard(hotel.getId()));
                hotel.setAttractionList(getAttractionsForCard(hotel.getCityId()));
                hotel.setPrice(getPrice(hotel.getId()));
                hotel.setPlaceName(getPlaceName(hotel.getId()));
            }
        }
        return hotelList;
    }

    public List<Hotel> getAll(String fromDate, String toDate,  double size, long room) {
        List<Hotel> hotelList = hotelRepo.findAll(fromDate, toDate, size, room);
        for(Hotel hotel : hotelList){
            for(Map map : attractionById(hotel.getId())){
                hotel.setComfortList(getComfortsForCard(hotel.getId()));
                hotel.setAttractionList(getAttractionsForCard(hotel.getCityId()));
                hotel.setPrice(getPrice(hotel.getId()));
                hotel.setPlaceName(getPlaceName(hotel.getId()));
            }
        }
        return hotelList;
    }
}
