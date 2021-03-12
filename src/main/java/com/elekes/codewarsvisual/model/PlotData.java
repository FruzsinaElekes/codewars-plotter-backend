package com.elekes.codewarsvisual.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlotData {

    private String language;
    private List<DataPoint> dataPoints;
}
