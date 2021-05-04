package by.tms.strore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    User user;

    @OneToMany(cascade = CascadeType.ALL)
    List<DeviceNumber> deviceNumberList;
    OrderStatus orderStatus=OrderStatus.PROCESSED;
    public Order(User user, List<DeviceNumber> deviceNumberList) {
        this.user = user;
        this.deviceNumberList = deviceNumberList;
    }
}
