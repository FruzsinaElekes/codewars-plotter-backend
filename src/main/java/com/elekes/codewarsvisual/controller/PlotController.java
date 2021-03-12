package com.elekes.codewarsvisual.controller;

import com.elekes.codewarsvisual.model.PlotData;
import com.elekes.codewarsvisual.service.PlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PlotController {

    @Autowired
    private PlotService plotService;

    @GetMapping("/plot/{language}")
    public PlotData getPlotDataForLanguage(@PathVariable String language,
                                           @RequestParam String username,
                                           @RequestParam String apiKey) {
        return plotService.getPlotDataForLanguage(language, username, apiKey);
    }

}
