package com.elekes.codewarsvisual.service;

import com.elekes.codewarsvisual.entity.Kata;
import com.elekes.codewarsvisual.model.plot.DataPoint;
import com.elekes.codewarsvisual.model.plot.PlotData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlotService {

    @Autowired
    private DataRetrievalService dataRetrievalService;


    public PlotData getPlotDataForLanguage(String language, String username) {
        Collection<Kata> completedKatas = dataRetrievalService.getCompletedKatasPerLanguage(language, username).values();
        return createPlotForKatas(completedKatas, language);
    }

    private PlotData createPlotForKatas(Collection<Kata> completedKatas, String language) {
        Map<String, Long> countByRank = completedKatas
                .stream()
                .filter(k -> k.getRank() != null)
                .collect(Collectors.groupingBy(Kata::getRank, Collectors.counting()));
        List<DataPoint> points = new ArrayList<>();
        countByRank.forEach((rank, count) -> {
            String label = String.format("%s (%s: %s)", count, language, rank);
            points.add(new DataPoint(count, rank, label));
        });
        Collections.reverse(points);
        PlotData plotData = PlotData.builder()
                .dataPoints(points)
                .language(language)
                .totalCount(completedKatas.size())
                .build();
        return plotData;
    }

}
