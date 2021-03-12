package com.elekes.codewarsvisual.service;

import com.elekes.codewarsvisual.entity.Kata;
import com.elekes.codewarsvisual.model.DataPoint;
import com.elekes.codewarsvisual.model.PlotData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PlotService {

    @Autowired
    private DataRetrievalService dataRetrievalService;

    public PlotData getPlotDataForLanguage(String language, String username, String apiKey) {
        List<Kata> completedKatas = dataRetrievalService.getCompletedKatasPerLanguage(language, username, apiKey);
        Map<String, Long> countByRank = completedKatas
                .stream()
                .collect(Collectors.groupingBy(Kata::getRank, Collectors.counting()));
        PlotData plotData = new PlotData();
        List<DataPoint> points = new ArrayList<>();
        countByRank.forEach((rank, count) -> {points.add(new DataPoint(count, rank));});
        plotData.setDataPoints(points);
        plotData.setLanguage(language);
        return plotData;
    }

}
