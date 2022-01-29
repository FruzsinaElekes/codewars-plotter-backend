package com.elekes.codewarsvisual.model.plot;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

/***
 * Class representing information about progress for a single language (number of Katas solved per rank),
 * to be displayed as a plot on the Statistics page
 */
@Data
@NoArgsConstructor
@Builder
public class PlotData {

    private String language;
    private List<DataPoint> dataPoints;
    private int totalCount;

    public PlotData(String language, List<DataPoint> dataPoints, int totalCount) {
        this.language = language;
        this.dataPoints = handleCountZero(dataPoints);
        this.totalCount = totalCount;
    }

    private List<DataPoint> handleCountZero(List<DataPoint> dataPoints) {
        Set<String> necessary = Set.of("8 kyu", "7 kyu", "6 kyu", "5 kyu", "4 kyu", "3 kyu", "2 kyu", "1 kyu");
        Set<String> existing = dataPoints.stream().map(DataPoint::getX).collect(Collectors.toSet());
        List<DataPoint> missing = necessary
                .stream()
                .filter(n -> !existing.contains(n))
                .map(n -> new DataPoint(0l, n, "0"))
                .collect(Collectors.toList());
        dataPoints.addAll(missing);
        Collections.sort(dataPoints);
        return dataPoints;
    }

}
