package ru.multicon.pointclick.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.multicon.pointclick.domain.PageClick;

import java.time.ZonedDateTime;

public interface PageClickRepository extends CrudRepository<PageClick, Long> {

    // Общее количество посещений за указанный период
    @Query(value = "SELECT COUNT(*) AS pageClicks \n" +
            "FROM t_page_click \n" +
            "WHERE date >= :dateStart \n" +
            "AND date <= :dateEnd ;",
            nativeQuery = true)
    Long getPageClicks(@Param("dateStart") ZonedDateTime dateStart,
                       @Param("dateEnd") ZonedDateTime dateEnd);

    // Количество уникальных пользователей за указанный период
    @Query(value = "SELECT COUNT(DISTINCT user_id) as usersUniq \n" +
            "FROM t_page_click \n" +
            "WHERE date >= :dateStart \n" +
            "AND date <= :dateEnd ;",
            nativeQuery = true)
    Long getUsersUniq(@Param("dateStart") ZonedDateTime dateStart,
                      @Param("dateEnd") ZonedDateTime dateEnd);

    // Количество постоянных пользователей за указанный период (пользователей,
    // которые за период просмотрели не менее 10 различных страниц)
    @Query(value = "SELECT COUNT(*) FROM (\n" +
            "SELECT COUNT(DISTINCT page_id) AS p\n" +
            "FROM t_page_click\n" +
            "WHERE date >= :dateStart \n" +
            "AND date <= :dateEnd \n" +
            "GROUP BY user_id) t\n" +
            "WHERE p >= 10;",
            nativeQuery = true)
    Long getUsersTop(@Param("dateStart") ZonedDateTime dateStart,
                     @Param("dateEnd") ZonedDateTime dateEnd);
}
