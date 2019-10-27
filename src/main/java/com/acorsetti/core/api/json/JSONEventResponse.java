package com.acorsetti.core.api.json;

import com.acorsetti.core.model.dto.EventDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JSONEventResponse extends JsonResponse<EventDto> {

    public JSONEventResponse(){}

    public JSONEventResponse(int results, List<EventDto> eventDtos) {
        super(results,eventDtos);
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("api")
    private void unpackNested(Map<String,Object> eventsMap){
        List<Object> eventsList = (List<Object>) eventsMap.get("events");
        super.setDataList(new ArrayList<>());
        eventsList.forEach( (eventMapObject) -> {
            Map<String,Object> eventMap = (Map<String,Object>) eventMapObject;
            int elapsed = (int) eventMap.get("elapsed");
            String teamId = eventMap.get("team_id") != null ? String.valueOf( (int) eventMap.get("team_id")) : null;
            String teamName = (String) eventMap.get("teamName");
            String player_id = eventMap.get("player_id") != null ? String.valueOf( (int) eventMap.get("player_id")) : null;
            String player = (String) eventMap.get("player");
            String assist_id = eventMap.get("assist_id") != null ? String.valueOf( (int) eventMap.get("assist_id")) : null;
            String assist = (String) eventMap.get("assist");
            String type = (String) eventMap.get("type");
            String detail = (String) eventMap.get("detail");

            EventDto eventDto = new EventDto(elapsed, teamId, teamName, player_id, player, assist_id, assist, type, detail);
            super.getDataList().add(eventDto);
        });

        super.setResults(super.getDataList().size());
    }

}
