package pdp.uz.spring_jpa_crud_hotel.entity;

import lombok.Data;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int number;
    private int floor;
    private double size;
    @ManyToOne
    private Hotel hotel;

}
