package by.tms.strore.service;

import by.tms.strore.entity.Device;
import by.tms.strore.entity.DeviceNumber;
import by.tms.strore.exception.DeviceAlreadyExistsException;
import by.tms.strore.repository.DeviceNumberRepository;
import by.tms.strore.repository.DeviceRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class DeviceNumberService {
    @Autowired
    private DeviceNumberRepository deviceNumberRepository;

    public DeviceNumber createDeviceNumber(DeviceNumber deviceNumber) {
           return deviceNumberRepository.save(deviceNumber);
    }
}
