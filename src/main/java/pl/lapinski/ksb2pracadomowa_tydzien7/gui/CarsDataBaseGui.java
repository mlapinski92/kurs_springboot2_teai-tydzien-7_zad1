package pl.lapinski.ksb2pracadomowa_tydzien7.gui;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import pl.lapinski.ksb2pracadomowa_tydzien7.model.Car;
import pl.lapinski.ksb2pracadomowa_tydzien7.service.CarDao;

import java.util.Optional;

@Route("cars-db")
public class CarsDataBaseGui extends VerticalLayout {

    private CarDao carDao;

    public CarsDataBaseGui(CarDao carDao) {
        this.carDao = carDao;

        Grid<Car> grid = new Grid<>(Car.class);
        grid.setItems(carDao.findAll());

        Accordion accordion = new Accordion();
        HorizontalLayout horizontalLayoutForSearch = new HorizontalLayout();

        NumberField lowerBoundProductionYear = new NumberField("FROM");
        NumberField upperBoundProductionYear = new NumberField("TO");
        Button searchButton = new Button("Search");

        searchButton.addClickListener(buttonClickEvent -> {
            grid.setItems(carDao.findCarByProductionDate(
                    lowerBoundProductionYear.getValue().longValue(),
                    upperBoundProductionYear.getValue().longValue()));
        });

        horizontalLayoutForSearch.add(lowerBoundProductionYear, upperBoundProductionYear, searchButton);
        horizontalLayoutForSearch.setVerticalComponentAlignment(Alignment.END, searchButton);

        accordion.add("Search by production year", horizontalLayoutForSearch);

        HorizontalLayout horizontalLayoutForAddCar = new HorizontalLayout();

        NumberField numberFieldId = new NumberField("ID");
        TextField textFieldMark = new TextField("Mark");
        TextField textFieldModel = new TextField("Model");
        NumberField productionYearField = new NumberField("Production Year");
        TextField colourField = new TextField("Colour");
        Button buttonAdd = new Button("Add car");

        Dialog dialogCar = new Dialog();
        dialogCar.setWidth("400px");
        dialogCar.setHeight("150px");

        buttonAdd.addClickListener(buttonClickEvent -> {
            Optional<Car> car = carDao.findCarById(numberFieldId.getValue().longValue());
            dialogCar.removeAll();

            if (car.isPresent()) {
                dialogCar.add(new Label("There is already a car with this ID"));
            } else {
                if (carDao.saveCar(numberFieldId.getValue().longValue(), textFieldMark.getValue(),
                        textFieldModel.getValue(), productionYearField.getValue().longValue(), colourField.getValue()
                )) {
                    dialogCar.add(new Label("Car added succesfully"));
                } else {
                    dialogCar.add(new Label("Error"));
                }
            }
            dialogCar.open();
            grid.setItems(carDao.findAll());
            numberFieldId.clear();
            textFieldModel.clear();
            textFieldMark.clear();
            productionYearField.clear();
            colourField.clear();
        });

        horizontalLayoutForAddCar.add(numberFieldId, textFieldMark, textFieldModel,
                productionYearField, colourField, buttonAdd);
        horizontalLayoutForAddCar.setVerticalComponentAlignment(Alignment.END, buttonAdd);
        accordion.add("Add car", horizontalLayoutForAddCar);

        add(grid, accordion);
    }
}
