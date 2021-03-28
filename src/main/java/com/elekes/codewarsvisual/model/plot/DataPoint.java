package com.elekes.codewarsvisual.model.plot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataPoint implements Comparable {

    private double y;
    private String x;
    private String longLabel;

    @Override
    public int compareTo(Object o) {
        if (this.x.compareTo(((DataPoint)o).getX()) > 0) return -1;
        return 1;
    }
}
