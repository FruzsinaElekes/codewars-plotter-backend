package com.elekes.codewarsvisual.controller;

import com.elekes.codewarsvisual.model.plot.PlotData;
import com.elekes.codewarsvisual.model.user.KataCompletionDetail;
import com.elekes.codewarsvisual.model.user.UserSummary;
import com.elekes.codewarsvisual.service.CodeWarsService;
import com.elekes.codewarsvisual.service.DataRetrievalService;
import com.elekes.codewarsvisual.service.PlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/users")
public class UserDataController {

    @Autowired
    private CodeWarsService codeWarsService;

    @Autowired
    private PlotService plotService;

    @Autowired
    private DataRetrievalService dataRetrievalService;

    @GetMapping("/{username}")
    public UserSummary getUserData(@PathVariable String username) {
        return codeWarsService.getUserFromCodeWars(username);
    }

    @GetMapping("/{username}/plots/{language}")
    public PlotData getPlotDataForLanguage(@PathVariable String language, @PathVariable String username) {
        return plotService.getPlotDataForLanguage(language, username);
    }

    @GetMapping("/{username}/katas")
    public List<KataCompletionDetail> getKatas(@PathVariable String username) {
        List<KataCompletionDetail> res = dataRetrievalService.getKataCompletionListForUser(username);
        return res;
    }
}
