package com.example.task1.repository;

import com.example.task1.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room,Integer>{

    Page<Room>findRoomByHotelId(Integer hotel_id, Pageable pageable);
}
