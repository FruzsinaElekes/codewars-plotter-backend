package com.elekes.codewarsvisual.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Kata {

    @Id
    @GeneratedValue
    private Long id;

    private String codewarsId;
    private String name;
    private String slug;
    private String category;
    @ElementCollection
    private List<String> languages = new LinkedList<>();
    private String rank;
    private String url;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String createdBy;
    @ElementCollection
    private List<String> tags = new LinkedList<>();



}
