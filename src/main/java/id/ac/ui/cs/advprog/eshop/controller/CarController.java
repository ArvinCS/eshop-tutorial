package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarService service;

    @GetMapping("/createCar")
    public String createCarPage(Model model) {
        Car car = new Car();
        model.addAttribute("car", car);
        return "CreateCar";
    }

    @PostMapping("/createCar")
    public String createCarPost(@ModelAttribute Car car, Model model) {
        service.create(car);
        return "redirect:listCar";
    }

    @GetMapping("/listCar")
    public String listCarPage(Model model) {
        List<Car> cars = service.findAll();
        model.addAttribute("cars", cars);
        return "CarList";
    }

    @GetMapping("/editCar/{id}")
    public String editCarPage(@PathVariable("id") String id, Model model) {
        Car car = service.findById(id);
        model.addAttribute("car", car);
        return "EditCar";
    }

    @PostMapping("/editCar")
    public String editCarPost(@ModelAttribute Car car, Model model) {
        service.update(car.getCarId(), car);
        return "redirect:listCar";
    }

    @PostMapping("/deleteCar")
    public String deleteCar(@RequestParam("id") String id, Model model) {
        service.deleteCarById(id);
        return "redirect:listCar";
    }
}
