package pdp.uz.spring_jpa_crud_hotel.controlller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pdp.uz.spring_jpa_crud_hotel.entity.Hotel;
import pdp.uz.spring_jpa_crud_hotel.repository.HotelRepository;
import pdp.uz.spring_jpa_crud_hotel.repository.RoomRepository;

import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;


    //C---CREATE HOTEL
    @PostMapping
    public String addHotel(@RequestBody Hotel hotel){
        boolean exists = hotelRepository.existsByName(hotel.getName());
        if(exists)
            return "Hotel already exist by name="+hotel.getName();
        hotelRepository.save(hotel);
        return "Hotel saved successfully";
    }

    //R---READ HOTEL
    @GetMapping
    public Page readHotel(@RequestParam int page){
        Pageable pageable= PageRequest.of(page,10);
        Page<Hotel> hotelPage = hotelRepository.findAll(pageable);
        return hotelPage;
    }

    //U---UPDATE HOTEL
    @PostMapping("/updateByHotelId/{id}")
    public String updateHotel(@RequestBody Hotel hotel,@PathVariable Integer id){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
       hotel.setId(optionalHotel.get().getId());
        if(!optionalHotel.isPresent())
            return "Hotel not found by id="+hotel.getName();
        hotelRepository.save(hotel);
        return "Hotel updated successfully";
    }

    //R---READ HOTEL
    @DeleteMapping("/deleteById/{id}")
    public String deleteHotel(@PathVariable Integer id){
        boolean exists = hotelRepository.existsById(id);
         if(exists)
             return "Hotel not found by id="+id;
        hotelRepository.deleteById(id);
        return "Hotel deleted by id="+id;
    }

}
