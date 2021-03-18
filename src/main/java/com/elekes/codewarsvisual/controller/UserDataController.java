package com.elekes.codewarsvisual.controller;

import com.elekes.codewarsvisual.model.plot.PlotData;
import com.elekes.codewarsvisual.model.user.UserSummary;
import com.elekes.codewarsvisual.service.CodeWarsService;
import com.elekes.codewarsvisual.service.PlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/users")
public class UserDataController {

    @Autowired
    private CodeWarsService codeWarsService;

    @Autowired
    private PlotService plotService;

    @GetMapping("/{username}")
    public UserSummary getUserData(@PathVariable String username) {

        System.out.println();
        return codeWarsService.getUserFromCodeWars(username);
    }

    @GetMapping("/{username}/plot/{language}")
    public PlotData getPlotDataForLanguage(@PathVariable String language, @PathVariable String username) {
        return plotService.getPlotDataForLanguage(language, username);
    }
}
