package pdp.uz.spring_jpa_crud_hotel.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
    private int number;
    private int floor;
    private double size;
    private int homeId;
}
