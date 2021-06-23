package com.vladbadey.demo.dto.request;

import lombok.Data;

@Data
public class ChapterRequestDto {

    private String name;

    private String content;

    private Long composition_id;
}
