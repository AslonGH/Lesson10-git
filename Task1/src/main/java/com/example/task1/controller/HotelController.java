package com.example.task1.controller;

import com.example.task1.entity.Hotel;
import com.example.task1.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController(value = "/hotel")
public class HotelController {

    @Autowired
    HotelRepository hotelRepository;

    @PostMapping
    public String addHotel(@RequestBody Hotel hotel){
        Hotel hotel1=new Hotel();
        hotel1.setName(hotel.getName());
        hotelRepository.save(hotel1);
     //   hotelRepository.save(hotel);
        return "Hotel added";
      }

    @GetMapping
    public List<Hotel>getHotels(){
        List<Hotel> hotelList = hotelRepository.findAll();
        return hotelList;
    }

    @PutMapping("/{id}")
    public String editHotel(@PathVariable Integer id,@RequestBody Hotel hotel){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (!optionalHotel.isPresent())
        return "Hotel not found";
        Hotel hotel1 = optionalHotel.get();
        hotel1.setName(hotel.getName());
        hotelRepository.save(hotel1);
        return "Hotel edited";
    }

     @DeleteMapping("/{id}")
     public String deleteHotel(@PathVariable Integer id){
     try {
      hotelRepository.deleteById(id);
      return "Hotel deleted";
     }catch (Exception e){
      return "Error in deleting";
     }

}


}

