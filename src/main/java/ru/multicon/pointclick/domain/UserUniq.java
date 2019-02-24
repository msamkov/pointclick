package ru.multicon.pointclick.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserUniq {
     Long pageClicks; // Общее количество посещений за текущие сутки
     Long usersUniq; // Количество уникальных пользователей за текущие сутки
}
