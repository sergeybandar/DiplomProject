package by.tms.strore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Baskets")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    List<DeviceNumber> deviceNumbers = new ArrayList<>();

    public void addDevice(Device device) {
        if (deviceNumbers.size() == 0) {
            deviceNumbers.add(new DeviceNumber(device, 1));
            return;
        }
        for (int i = 0; i < deviceNumbers.size(); i++) {
            if (deviceNumbers.get(i).getDevice().equals(device)) {
                deviceNumbers.get(i).setNumber((deviceNumbers.get(i).getNumber() + 1));
                return;
            }
        }
        deviceNumbers.add(new DeviceNumber(device, 1));
    }

    public int priceDevices() {
        int price = 0;
        for (int i = 0; i < deviceNumbers.size(); i++) {
            price = price + deviceNumbers.get(i).getNumber() * Integer.parseInt(deviceNumbers.get(i).getDevice().getPrice());
        }
        return price;
    }

    public void deleteDevice(Device device) {
        for (int i = 0; i < deviceNumbers.size(); i++) {
            if (deviceNumbers.get(i).getDevice().equals(device)) {
                deviceNumbers.remove(i);
            }
        }
    }
}
