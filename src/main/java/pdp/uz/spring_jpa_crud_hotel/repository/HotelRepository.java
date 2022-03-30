package pdp.uz.spring_jpa_crud_hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.spring_jpa_crud_hotel.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel,Integer> {
    boolean existsByName(String name);
}
