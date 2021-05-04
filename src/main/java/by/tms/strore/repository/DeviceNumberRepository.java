package by.tms.strore.repository;

import by.tms.strore.entity.Device;
import by.tms.strore.entity.DeviceNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceNumberRepository extends JpaRepository<DeviceNumber, Long> {
}
