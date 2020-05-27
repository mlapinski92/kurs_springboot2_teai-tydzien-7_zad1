package pl.lapinski.ksb2pracadomowa_tydzien7.service;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import pl.lapinski.ksb2pracadomowa_tydzien7.model.Car;

import java.util.List;
import java.util.Optional;

@Service
@Repository
public interface CarDao {

    boolean saveCar(long id, String mark, String model, long productionYear, String colour);

    List<Car> findAll();

    Optional<Car> findCarById(long id);

    List<Car> findCarByProductionDate(long lowerBoundYear, long upperBoundYear);
}
