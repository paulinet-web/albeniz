package com.theodo.albeniz.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Tune {

    @Getter @Setter
    private int id;
    @Getter @Setter @NotBlank(message = "title is mandatory")
    private String title;
    @Getter @Setter
    private String author;


}
