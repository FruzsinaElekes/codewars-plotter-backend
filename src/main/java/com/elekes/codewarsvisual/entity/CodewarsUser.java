package com.elekes.codewarsvisual.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class CodewarsUser {
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String api_key;
    private String password;

}
