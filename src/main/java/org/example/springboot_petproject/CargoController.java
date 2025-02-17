package org.example.springboot_petproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class CargoController {
    @Autowired
    private CargoService service;

    @RequestMapping("/")
    public String index(Model model, @Param("keyword") String keyword) {
        List<Cargo> listCargo = service.listAll(keyword);
        model.addAttribute("listCargo", listCargo);
        model.addAttribute("keyword", keyword);
        Integer totalPrice = service.getTotalPrice();
        model.addAttribute("totalPrice", totalPrice);
        return "index";
    }

    @PostMapping(value="/sort_date")
    public String orderDate(Model model) {
        // Добавляем данные в модель
        List<Cargo> listCargo = service.sort_by_date();
        model.addAttribute("listCargo", listCargo);

        Integer totalPrice = service.getTotalPrice();
        model.addAttribute("totalPrice", totalPrice);
        return "index";
    }

    @RequestMapping(value="/sort_price")
    public String orderPrice(Model model) {
        // Добавляем данные в модель
        List<Cargo> listCargo = service.sort_by_price();
        model.addAttribute("listCargo", listCargo);

        Integer totalPrice = service.getTotalPrice();
        model.addAttribute("totalPrice", totalPrice);
        return "index";
    }

    @RequestMapping("/new")
    public String newCargo(Model model) {
        model.addAttribute("cargo", new Cargo());
        List<String> cities = List.of("Москва", "Санкт-Петербург", "Казань", "Новосибирск", "Екатеринбург");
        model.addAttribute("cities", cities);
        return "new";
    }

    @RequestMapping(value = "/save")
    public String saveCargo(@ModelAttribute("cargo") Cargo cargo) {
        service.save(cargo);
        return "redirect:/";
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editCargo(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Cargo cargo = service.findById(id);
        modelAndView.addObject("cargo", cargo);
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}")
    public String deleteCargo(@PathVariable Integer id) {
        service.deleteById(id);
        return "redirect:/";
    }

    // Метод для отображения графика
    @RequestMapping("/graph")
    public ModelAndView showGraphPage() {
        // Получаем данные статистики из сервиса
        List<DayStatistics> stat = service.getDailyStatistics();

        // Добавляем данные в модель
        ModelAndView modelAndView = new ModelAndView("graph");
        modelAndView.addObject("stat", stat);
        return modelAndView;
    }

    @RequestMapping("/day")
    public ModelAndView showDayAmount() {
        List<DayStatistics> dayAmount = service.getDailyStatistics();
        ModelAndView modelAndView = new ModelAndView("day");
        modelAndView.addObject("dayAmount", dayAmount);
        return modelAndView;
    }

    @RequestMapping("/search")
    public ModelAndView searchData() {
        // Добавляем данные в модель
        ModelAndView modelAndView = new ModelAndView("search");
        modelAndView.addObject("filters", new Cargo());
//        List<Cargo> filteredList = service.getByParams(new Cargo());
//        modelAndView.addObject("filteredList", filteredList);
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/search")
    public ModelAndView searchData(@ModelAttribute("filters") Cargo filter) {
        ModelAndView modelAndView = new ModelAndView("search");
        List<Cargo> filteredList = service.getByParams(filter); // Получаем список по параметрам
        modelAndView.addObject("filters", filter); // Возвращаем введённые фильтры
        modelAndView.addObject("filteredList", filteredList); // Возвращаем результаты поиска
        return modelAndView;
    }

//    @RequestMapping("/addFilteredData")
//    public String saveFiltres(@ModelAttribute("filteredList") List<Cargo> filteredList, @ModelAttribute("filters") Cargo filters) {
//        filteredList = service.getByParams(filters);
//
//        return "redirect:/search";
//    }



//    @RequestMapping("/statistics")
//    public ModelAndView getCargoStatistics() {
//        ModelAndView modelAndView = new ModelAndView("graph");
//        List<DayStatistics> stat = service.getDailyStatistics();
//        modelAndView.addObject("stat", stat);
//        return modelAndView;
//    }





//    @RequestMapping("/search")
//    public String searchCargo(Model model, @ModelAttribute("filter") Cargo filter) {
//        model.addAttribute("filter", new Cargo());
//        List<Cargo> listCargo = service.listFiltered(filter);
//        model.addAttribute("listCargo", listCargo);
//        return "search";
//
////        model.addAttribute("params", new Cargo());
////        return "search";
//    }
//
//    @RequestMapping("/listFiltered")
//    public String searchCargo(Model model, @ModelAttribute("filter") Cargo filter) {
//        model.addAttribute("filter", new Cargo());
//        List<Cargo> listCargo = service.listFiltered(filter);
//        model.addAttribute("listCargo", listCargo);
//        return "search";
//    }
}

