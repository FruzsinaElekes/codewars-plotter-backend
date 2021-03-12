package com.elekes.codewarsvisual.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

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
        this.dataPoints = dataPoints;
        this.totalCount = totalCount;
        this.percentageDataPoints = calculatePercentages();
    }

    private List<DataPoint> calculatePercentages() {
        List<DataPoint> percentages = new ArrayList<>();
        for (DataPoint point : dataPoints) {
            percentages.add(new DataPoint(point.getY()*100/totalCount, point.getLabel()));
        }
        return percentages;
    }
}
