package com.example.task1.controller;

import com.example.task1.entity.Hotel;
import com.example.task1.entity.Room;
import com.example.task1.payload.RoomDto;
import com.example.task1.repository.HotelRepository;
import com.example.task1.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
public class RoomController {
    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    RoomRepository roomRepository;

    @PostMapping
    public String addRoom(@RequestBody RoomDto roomDto){
        Room room=new Room();
        room.setNumber(roomDto.getNumber());
        room.setFloor(roomDto.getFloor());
        room.setSize(roomDto.getSize());
        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
        if (!optionalHotel.isPresent())
        return "Hotel not found";
            room.setHotel(optionalHotel.get());
            roomRepository.save(room);
        return "Room added";
    }

    @GetMapping("/{hotelId}")
    public Page<Room> getHotels(@PathVariable Integer hotelId, int page){
        Pageable pageable = PageRequest.of(page, 10);
        Page<Room> roomByHotelId = roomRepository.findRoomByHotelId(hotelId, pageable);
        return roomByHotelId;
    }

    @PutMapping("/{id}")
    public String editRoom(@PathVariable Integer id,@RequestBody RoomDto roomDto){

        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (!optionalRoom.isPresent())
            return "Room not found";
        Room room = optionalRoom.get();
        room.setFloor(roomDto.getFloor());
        room.setSize(roomDto.getSize());
        room.setNumber(roomDto.getNumber());

        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
        if (optionalHotel.isPresent())
        return "Hotel not found";
        room.setHotel(optionalHotel.get());
        roomRepository.save(room);
        return "Room edited";
    }

    @DeleteMapping("/{id}")
    public String deleteRoom(@PathVariable Integer id){
        try {
            roomRepository.deleteById(id);
            return "Room deleted";
        }catch (Exception e){
            return "Error in deleting";
        }

    }

}
