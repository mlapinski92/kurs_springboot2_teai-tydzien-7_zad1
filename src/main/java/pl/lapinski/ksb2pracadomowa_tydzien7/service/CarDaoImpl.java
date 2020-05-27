package pl.lapinski.ksb2pracadomowa_tydzien7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import pl.lapinski.ksb2pracadomowa_tydzien7.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Repository
public class CarDaoImpl implements CarDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CarDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean saveCar(long id, String mark, String model, long productionYear, String colour) {
        Car newCar = new Car(id, mark, model, productionYear, colour);
        String sql = "INSERT INTO cars VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql, newCar.getCarId(), newCar.getMark(), newCar.getModel(),
                newCar.getProductionYear(), newCar.getColour());
        if (mark != null && model != null) {
            return true;
        }
        return false;
    }

    @Override
    public List<Car> findAll() {
        String sql = "SELECT * FROM cars";
        List<Car> carList = new ArrayList<>();
        List<Map<String, Object>> allCars = jdbcTemplate.queryForList(sql);
        allCars.stream().forEach(element -> carList.add(new Car(
                Long.parseLong(String.valueOf(element.get("car_id"))),
                String.valueOf(element.get("mark")),
                String.valueOf(element.get("model")),
                Long.parseLong(String.valueOf(element.get("production_year"))),
                String.valueOf(element.get("colour"))
        )));
        return carList;
    }

    @Override
    public Optional<Car> findCarById(long id) {
        return findAll().stream().filter(car -> car.getCarId() == id).findFirst();
    }

    @Override
    public List<Car> findCarByProductionDate(long lowerBoundYear, long upperBoundYear) {
        String sql = "SELECT * FROM cars WHERE production_year BETWEEN ? AND ? ";
        List<Car> carList = new ArrayList<>();
        List<Map<String, Object>> allCars = jdbcTemplate.queryForList(sql, lowerBoundYear, upperBoundYear);
        allCars.stream().forEach(element -> carList.add(new Car(
                Long.parseLong(String.valueOf(element.get("car_id"))),
                String.valueOf(element.get("mark")),
                String.valueOf(element.get("model")),
                Long.parseLong(String.valueOf(element.get("production_year"))),
                String.valueOf(element.get("colour"))
        )));
        return carList;
    }
}
