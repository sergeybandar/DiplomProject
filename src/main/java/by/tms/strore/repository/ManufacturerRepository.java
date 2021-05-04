package by.tms.strore.repository;

import by.tms.strore.entity.Category;
import by.tms.strore.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    boolean existsManufacturerByName(String name);
    void deleteManufacturerByName(String name);
    Manufacturer findManufacturerByName(String name);
    Manufacturer findManufacturerById(long id);
    boolean existsManufacturerById(long id);
}
