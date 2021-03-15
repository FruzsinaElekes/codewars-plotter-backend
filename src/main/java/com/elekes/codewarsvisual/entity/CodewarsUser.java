package com.elekes.codewarsvisual.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CodewarsUser {
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String api_key;
    private String password;

}
