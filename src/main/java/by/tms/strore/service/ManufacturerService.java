package by.tms.strore.service;

import by.tms.strore.entity.Category;
import by.tms.strore.entity.Manufacturer;
import by.tms.strore.exception.CategoryAlreadyExistsException;
import by.tms.strore.exception.CategoryNotFoundException;
import by.tms.strore.exception.ManufacturerAlreadyExistsException;
import by.tms.strore.exception.ManufacturerNotFoundException;
import by.tms.strore.repository.CategoryRepository;
import by.tms.strore.repository.ManufacturerRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class ManufacturerService {
    @Autowired
    private ManufacturerRepository manufacturerRepository;

    public void createManufacturer(Manufacturer manufacturer) {
        if (!manufacturerRepository.existsManufacturerByName(manufacturer.getName())) {
            manufacturerRepository.save(manufacturer);
        } else {
            throw new ManufacturerAlreadyExistsException("Manufacturer "+manufacturer.getName()+" is exists");
        }
    }

    public List<Manufacturer> getAllManufacturers() {
        List<Manufacturer> manufacturers = manufacturerRepository.findAll();
        if (manufacturers != null) {
            return manufacturers;
        } else {
            throw new ManufacturerNotFoundException("Categories not found");
        }
    }

    public Manufacturer findManufacturerByName(String name) {
        if (manufacturerRepository.existsManufacturerByName(name)) {
            return manufacturerRepository.findManufacturerByName(name);
        } else {
            throw new ManufacturerNotFoundException("Category not found");
        }
    }
}
