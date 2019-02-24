package ru.multicon.pointclick.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "t_page_click")
public class PageClick {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    Long userId; // Идентификатор пользователя

    @Column(name = "page_id")
    Long pageId; // Идентификатор страницы сайт

    @Column(name = "date")
    ZonedDateTime dateTime;

}
