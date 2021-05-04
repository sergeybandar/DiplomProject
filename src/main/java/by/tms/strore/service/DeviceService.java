package by.tms.strore.service;

import by.tms.strore.entity.Category;
import by.tms.strore.entity.Device;
import by.tms.strore.exception.CategoryAlreadyExistsException;
import by.tms.strore.exception.CategoryNotFoundException;
import by.tms.strore.exception.DeviceAlreadyExistsException;
import by.tms.strore.exception.DeviceNotFoundException;
import by.tms.strore.repository.CategoryRepository;
import by.tms.strore.repository.DeviceRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Data
@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    public void createDevice(Device device) {
        if (!deviceRepository.existsDeviceByName(device.getName())) {
            deviceRepository.save(device);
        } else {
            throw new DeviceAlreadyExistsException("Device is exists");
        }
    }
    public List<Device> getAllDevicesByCategory(Category category) {
        List<Device> devices = deviceRepository.findDevicesByCategory(category);
        if (devices != null) {
            return devices;
        } else {
            throw new DeviceNotFoundException("Devices by " +category.getName()+"not found");
        }
    }
    public List<Device> getAllDevices() {
        List<Device> devices = deviceRepository.findAll();
        if (devices != null) {
            return devices;
        } else {
            throw new DeviceNotFoundException("Devices not found");
        }
    }

    public Device findDeviceByName(String name) {
        if (deviceRepository.existsDeviceByName(name)) {
            return deviceRepository.findDeviceByName(name);
        } else {
            throw new DeviceNotFoundException("Device not found");
        }
    }
    public Device findDeviceById(Long id) {
        if (deviceRepository.existsDeviceById(id)) {
            return deviceRepository.findDeviceById(id);
        } else {
            throw new DeviceNotFoundException("Device not found");
        }
    }
}
