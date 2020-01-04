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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
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
    private FixtureService fixtureService;

    @Autowired
    private APIEventRetriever apiEventRetriever;

    @Autowired
    private EventRepository eventRepository;

    @Override
    @Scheduled(cron = "${cron.updateEvents}")
    public void updateCloseGoalEvents() {
        logger.info("Event update started...");

        LocalDate lowerBound = LocalDate.of(2017, Month.JANUARY, 1);
        LocalDate upperBound = LocalDate.of(2017, Month.JUNE, 29);

        List<Event> events = new ArrayList<>();
        List<Fixture> fixtures = this.fixtureService.fixturesInPeriodByAPI(lowerBound, upperBound);

        for(int i = 0; i < fixtures.size(); i++){
            Fixture fixture = fixtures.get(i);
            String id = fixture.getFixtureId();
            APIResponse<Event> apiResponse = apiEventRetriever.byFixtureId(id);
            events.addAll(apiResponse.getBody().stream().filter( event ->
                    event.getEventType().equals("Goal") &&
                    !event.getDetail().equals("Missed Penalty") &&
                    //check if this event is not already stored in repository
                    this.eventRepository.findAllByEventTypeAndFixtureIdAndElapsedAndTeamIdAndDetail(
                            event.getEventType(), id, event.getElapsed(), event.getTeamId(), event.getDetail()
                    ).isEmpty()
            ).collect(Collectors.toList()));

            logger.info("Updated events for fixture: " + id + " Progress: " + i + " / " + fixtures.size());

        }

        this.eventRepository.saveAll(events);
    }
}
