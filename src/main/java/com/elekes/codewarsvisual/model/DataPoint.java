package com.elekes.codewarsvisual.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataPoint implements Comparable {

    private Long y;
    private String label;

    @Override
    public int compareTo(Object o) {
        if (this.label.compareTo(((DataPoint)o).getLabel()) > 0) return -1;
        return 11;
    }
}
