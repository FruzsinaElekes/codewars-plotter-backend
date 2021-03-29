package com.elekes.codewarsvisual.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KataCompletionDetail {

    private String codewarsId;
    private String name;
    private String slug;
    private String category;
    private String rank;
    private String url;
    private String createdBy;
    private List<String> tags = new LinkedList<>();
    private List<String> completedLanguages;
    private String completedAt;
}
