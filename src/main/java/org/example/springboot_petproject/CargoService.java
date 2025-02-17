package org.example.springboot_petproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CargoService {
    @Autowired
    private CargoRepo repo;

    public List<Cargo> listAll(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }

    public void save(Cargo cargo) {
        repo.save(cargo);
    }

    public Cargo findById(Integer id) {
        return repo.findById(id).get();
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    public List<Cargo> listFiltered(Cargo cargo) {
        if (cargo.getId() == null && cargo.getName() == null && cargo.getContent() == null && cargo.getPrice() == null && cargo.getCity_send() == null && cargo.getDate_send() == null && cargo.getCity_arrive() == null && cargo.getDate_arrive() == null) {
            return repo.findAll();
        }
        return repo.findByParams(cargo.getId(), cargo.getName(), cargo.getContent(), cargo.getPrice(), cargo.getCity_send(), cargo.getDate_send(), cargo.getCity_arrive(), cargo.getDate_arrive());
    }

    public List<DayStatistics> getDailyStatistics() {
        List<Object[]> stats = repo.findDailyStatistics();
        List<DayStatistics> stat = new ArrayList<>(); // Инициализируем список

        for (Object[] line : stats) { // Указываем тип переменной line
            String date = (String) line[0]; // Приводим line[0] к String
            Long cargoCount = (Long) line[1]; // Приводим line[1] к Long
            Long totalCost = (Long) line[2]; // Приводим line[2] к Double

            // Преобразуем Long и Double в Integer
            stat.add(new DayStatistics(
                    date,
                    cargoCount != null ? cargoCount.intValue() : null,
                    totalCost != null ? totalCost.intValue() : null
            ));
        }

        return stat;
    }

    public Integer getTotalPrice() {
        List<Object[]> result = repo.findTotalPrice();
        Long LongPrice = (Long) result.get(0)[0];
        Integer totalPrice = LongPrice.intValue();
        return totalPrice;
    }

    public List<Cargo> getByParams(Cargo filters) {
        return repo.findByParams(filters.getId(), filters.getName(), filters.getContent(), filters.getPrice(), filters.getCity_send(), filters.getDate_send(), filters.getCity_arrive(), filters.getDate_arrive());
    }

    public List<Cargo> sort_by_date() {
        return repo.order_by_date();
    }

    public List<Cargo> sort_by_price() {
        return repo.order_by_price();
    }

}
