package com.elekes.codewarsvisual.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Builder
public class PlotData {

    private String language;
    private List<DataPoint> dataPoints;
    private List<DataPoint> percentageDataPoints;
    private int totalCount;

    public PlotData(String language, List<DataPoint> dataPoints, List<DataPoint> percentageDataPoints, int totalCount) {
        this.language = language;
        this.dataPoints = handleCountZero(dataPoints);
        this.totalCount = totalCount;
        this.percentageDataPoints = calculatePercentages();
    }

    private List<DataPoint> handleCountZero(List<DataPoint> dataPoints) {
        Set<String> necessary = Set.of("8 kyu", "7 kyu", "6 kyu", "5 kyu", "4 kyu", "3 kyu", "2 kyu", "1 kyu");
        Set<String> existing = dataPoints.stream().map(DataPoint::getLabel).collect(Collectors.toSet());
        List<DataPoint> missing = necessary
                .stream()
                .filter(n -> !existing.contains(n))
                .map(n -> new DataPoint(0l, n))
                .collect(Collectors.toList());
        dataPoints.addAll(missing);
        Collections.sort(dataPoints);
        return dataPoints;
    }

    private List<DataPoint> calculatePercentages() {
        List<DataPoint> percentages = new ArrayList<>();
        for (DataPoint point : dataPoints) {
            percentages.add(new DataPoint(point.getY()*100/totalCount, point.getLabel()));
        }
        return percentages;
    }
}
