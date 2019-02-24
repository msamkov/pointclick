package ru.multicon.pointclick.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.multicon.pointclick.domain.PageClick;
import ru.multicon.pointclick.domain.UserTop;
import ru.multicon.pointclick.domain.UserUniq;
import ru.multicon.pointclick.repositories.PageClickRepository;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class PageClickServiceImpl implements PageClickService {

    private final PageClickRepository pageClickRepository;

    @Autowired
    public PageClickServiceImpl(PageClickRepository pageClickRepository) {
        this.pageClickRepository = pageClickRepository;
    }

    @Transactional
    @Override
    public UserUniq create(Long user_id, Long page_id) {
        PageClick pageClick = new PageClick()
                .setUserId(user_id)
                .setPageId(page_id)
                .setDateTime(ZonedDateTime.now());
        pageClickRepository.save(pageClick);
        return getUserUniqByCurrentDay();
    }

    private UserUniq getUserUniqByCurrentDay() {
        ZonedDateTime dateStart = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS);
        ZonedDateTime dateEnd = dateStart
                .plus(Duration.of(1, ChronoUnit.DAYS))
                .minus(Duration.of(1, ChronoUnit.SECONDS));
        UserUniq userUniq = new UserUniq();
        userUniq.setPageClicks(pageClickRepository.getPageClicks(dateStart, dateEnd));
        userUniq.setUsersUniq(pageClickRepository.getUsersUniq(dateStart, dateEnd));
        return userUniq;
    }

    @Transactional
    @Override
    public UserTop statistics(ZonedDateTime dateStart, ZonedDateTime dateEnd) {
        UserTop userTop = new UserTop();
        userTop.setPageClicks(pageClickRepository.getPageClicks(dateStart, dateEnd));
        userTop.setUsersUniq(pageClickRepository.getUsersUniq(dateStart, dateEnd));
        userTop.setUsersTop(pageClickRepository.getUsersTop(dateStart, dateEnd));
        return userTop;
    }
}
