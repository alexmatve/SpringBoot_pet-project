package org.example.springboot_petproject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;

@EnableJpaRepositories
public interface CargoRepo extends JpaRepository<Cargo, Integer> {
    @Query("SELECT p FROM Cargo p WHERE CONCAT(p.id, p.name, p.content, p.price, p.city_send, p.date_send, p.city_arrive, p.date_arrive) LIKE  %?1%")
    List<Cargo> search(String keyword);

    @Query("SELECT c.date_send, COUNT(c.id), SUM(c.price) " +
            "FROM Cargo c GROUP BY c.date_send ORDER BY c.date_send")
    List<Object[]> findDailyStatistics();

    @Query("SELECT coalesce(SUM(c.price), 0) FROM Cargo c")
    List<Object[]> findTotalPrice();

    @Query("SELECT p FROM Cargo p ORDER BY p.date_arrive")
    List<Cargo> order_by_date();

    @Query("SELECT p FROM Cargo p ORDER BY p.price")
    List<Cargo> order_by_price();




    @Query("SELECT c FROM Cargo c " +
            "WHERE (:id IS NULL OR c.id = :id) " +
            "AND (:name IS NULL OR c.name LIKE %:name%) " +
            "AND (:content IS NULL OR c.content LIKE %:content%) " +
            "AND (:price IS NULL OR c.price = :price) " +
            "AND (:city_send IS NULL OR c.city_send LIKE %:city_send%) " +
            "AND (:date_send IS NULL OR c.date_send LIKE %:date_send%) " +
            "AND (:city_arrive IS NULL OR c.city_arrive LIKE %:city_arrive%) " +
            "AND (:date_arrive IS NULL OR c.date_arrive LIKE %:date_arrive%)"
    )
    List<Cargo> findByParams(
            @Param("id") Integer id,
            @Param("name") String name,
            @Param("content") String content,
            @Param("price") Integer price,
            @Param("city_send") String city_send,
            @Param("date_send") String date_send,
            @Param("city_arrive") String city_arrive,
            @Param("date_arrive") String date_arrive
    );


}
