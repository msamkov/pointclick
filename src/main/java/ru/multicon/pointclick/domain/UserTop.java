package ru.multicon.pointclick.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserTop {
    Long pageClicks; // Общее количество посещений за текущие сутки
    Long usersUniq; // Количество уникальных пользователей за текущие сутки
    Long usersTop; // Количество постоянных пользователей за указанный период (пользователей,
                   // которые за период просмотрели не менее 10 различных страниц)
}