package com.theodo.albeniz.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Tune {

    @Getter @Setter
    private String title;
    @Getter @Setter
    private String author;

}
