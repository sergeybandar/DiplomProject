package by.tms.strore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceModel {

    private String category;
    private String manufacturer;
    private String name;
    private String description;
    private String price;
    private String country;
    String url;

}
