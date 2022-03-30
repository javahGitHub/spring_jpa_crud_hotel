package pdp.uz.spring_jpa_crud_hotel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.spring_jpa_crud_hotel.entity.Room;

public interface RoomRepository extends JpaRepository<Room,Integer> {
    Page findAllByHotelId(Integer id, Pageable pageable);
}
