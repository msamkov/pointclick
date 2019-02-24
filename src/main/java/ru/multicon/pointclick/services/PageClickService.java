package ru.multicon.pointclick.services;
import ru.multicon.pointclick.domain.UserTop;
import ru.multicon.pointclick.domain.UserUniq;

import java.time.ZonedDateTime;

public interface PageClickService {
    UserUniq create(Long user_id, Long page_id);
    UserTop statistics(ZonedDateTime dateStart, ZonedDateTime dateEnd);
}
