package by.tms.strore.repository;

import by.tms.strore.entity.Category;
import by.tms.strore.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    boolean existsDeviceByName(String name);
    void deleteDeviceByName(String name);
    Device findDeviceByName(String name);
    Device findDeviceById(long id);
    List<Device> findDevicesByCategory(Category category);
    boolean existsDeviceById(long id);
}
