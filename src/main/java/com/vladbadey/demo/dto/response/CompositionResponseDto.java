package com.vladbadey.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompositionResponseDto {

    private String name;

    private String description;

    private String image;

    private String fandom;
}
