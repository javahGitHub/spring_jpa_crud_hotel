package pdp.uz.spring_jpa_crud_hotel.controlller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pdp.uz.spring_jpa_crud_hotel.DTO.RoomDto;
import pdp.uz.spring_jpa_crud_hotel.entity.Hotel;
import pdp.uz.spring_jpa_crud_hotel.entity.Room;
import pdp.uz.spring_jpa_crud_hotel.repository.HotelRepository;
import pdp.uz.spring_jpa_crud_hotel.repository.RoomRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    RoomRepository roomRepository;


    //C---CREATE ROOM
    @PostMapping("/crateByHotelId/{id}")
    public String addRoom(@PathVariable Integer id, @RequestBody RoomDto roomDto){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if(!optionalHotel.isPresent())
            return "Hotel not found by id="+id;
        Room room=new Room();
        room.setNumber(roomDto.getNumber());
        room.setFloor(roomDto.getFloor());
        room.setSize(room.getSize());
        room.setHotel(optionalHotel.get());
        roomRepository.save(room);
        return "Room added successfully";
    }

    //R---READ ROOMS
    @GetMapping
    public Page getRAllRooms(@RequestParam int page){
        Pageable pageable= PageRequest.of(page,10);
        Page<Room> roomPage = roomRepository.findAll(pageable);
        return roomPage;
    }


    //R---READ ROOM BY HOTEL ID
    @GetMapping("/getRoomByHotelId/{id}")
    public Page getRAllRoomsByHotelId(@PathVariable Integer id,@RequestParam int page){
        boolean exists = hotelRepository.existsById(id);
        if(exists)
            return (Page) Arrays.asList("Hotel not found when id="+id);

        Pageable pageable= PageRequest.of(page,10);
        Page<Room> roomPage = roomRepository.findAllByHotelId(id,pageable);
        return roomPage;
    }

    //U---UPDATE ROOM BY HOTEL ID
    @PostMapping("/{id}/updateRoomByHotelId/{hId}")
    public String updateRoomByHotelId(@PathVariable Integer id, @PathVariable Integer hId, @RequestParam int page){
        //Check hotel id in repository
        boolean exists = hotelRepository.existsById(hId);
        if(exists)
            return "Hotel not found when id=" ;
        //Check room id in repository
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if(!optionalRoom.isPresent())
            return "Room not found by id="+id;
        roomRepository.save(optionalRoom.get());
        return "Room updated successfully";
    }

    //D---DELETE ROOM BY HOTEL ID
    @GetMapping("/{id}/deleteRoomByHotelId/{hId}")
    public String getRAllRoomsByHotelId(@PathVariable Integer id,@PathVariable Integer hId,@RequestParam int page){
        //Check hotel id in repository
        boolean exists = hotelRepository.existsById(hId);
        if(exists)
            return "Hotel not found when id=";
        //Check room id in repository
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if(!optionalRoom.isPresent())
            return "Room not found by id="+id;
       roomRepository.deleteById(id);
       return "Room deleted successfully";
    }


}
