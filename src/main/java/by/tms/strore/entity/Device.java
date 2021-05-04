package by.tms.strore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Category category;
    @OneToOne(cascade = CascadeType.ALL)
    private Manufacturer manufacturer;
    private String name;
    private String description;
    private String price;
    private String country;
    private String url;

    public Device(Category category, Manufacturer manufacturer, String name, String description, String price, String country, String url) {
        this.category = category;
        this.manufacturer = manufacturer;
        this.name = name;
        this.description = description;
        this.price = price;
        this.country = country;
        this.url=url;
    }
}
