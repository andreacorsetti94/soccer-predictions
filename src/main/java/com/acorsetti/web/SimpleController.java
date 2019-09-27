package com.acorsetti.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SimpleController {

    @RequestMapping(path= "/sample",method= RequestMethod.GET)
    public ModelAndView greet(){
        ModelAndView mav = new ModelAndView("sample");
        Map<String,String> colMaps = new HashMap<>();
        colMaps.put("dateCol","Date");
        colMaps.put("homeTeamCol","Home Team");
        colMaps.put("awayTeamCol","Away Team");
        colMaps.put("hdaHomeCol","1");
        colMaps.put("hdaDrawCol","X");
        colMaps.put("hdaAwayCol","2");
        colMaps.put("U2_5ol","Under 2,5");
        colMaps.put("O2_5Col","Over 2,5");
        colMaps.put("GoalCol","Both teams to score");
        colMaps.put("noGoalCol","No Goal");


        mav.addAllObjects(colMaps);
        return mav;
    }
}
