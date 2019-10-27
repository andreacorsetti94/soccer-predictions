package com.acorsetti.core.updater.impl;

import com.acorsetti.core.api.APIEventRetriever;
import com.acorsetti.core.api.APIResponse;
import com.acorsetti.core.model.jpa.Event;
import com.acorsetti.core.model.jpa.Fixture;
import com.acorsetti.core.repository.EventRepository;
import com.acorsetti.core.service.FixtureService;
import com.acorsetti.core.updater.EventUpdater;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Configuration
@Service
@PropertySource("classpath:scheduler.properties")
public class EventUpdaterImpl implements EventUpdater {

    private static final Logger logger = Logger.getLogger(EventUpdaterImpl.class);

    @Autowired
    private Environment environment;

    @Autowired
    private FixtureService fixtureService;

    @Autowired
    private APIEventRetriever apiEventRetriever;

    @Autowired
    private EventRepository eventRepository;

    @Override
    @Scheduled(cron = "${cron.updateEvents}")
    public void updateCloseGoalEvents() {
        logger.info("Event update started...");
        int daysPriorToThisDay = Integer.parseInt(Objects.requireNonNull(this.environment.getProperty("daysBefore")));
        int daysAfterThisDay = Integer.parseInt(Objects.requireNonNull(this.environment.getProperty("daysAfter")));

        LocalDate now = LocalDate.now();
        LocalDate lowerBound = now.minusDays(daysPriorToThisDay);
        LocalDate upperBound = now.plusDays(daysAfterThisDay);

        List<Event> events = new ArrayList<>();
        List<Fixture> fixtures = this.fixtureService.fixturesInPeriodByDB(lowerBound, upperBound);
        fixtures.forEach( fixture -> {
            String id = fixture.getFixtureId();
            APIResponse<Event> apiResponse = apiEventRetriever.byFixtureId(id);
            List<Event> eventsBody = apiResponse.getBody();
            events.addAll(apiResponse.getBody().stream().filter( event ->
                event.getEventType().equals("Goal") && !event.getDetail().equals("Missed Penalty")
            ).collect(Collectors.toList()));

            logger.info("GoalEvents added: " + eventsBody.size() + " this day: " + fixture.getEventDate().toLocalDate());
        });
        this.eventRepository.saveAll(events);
    }
}
