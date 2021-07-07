package com.vladbadey.demo.mapper;

import com.vladbadey.demo.dto.request.ChapterRequestDto;
import com.vladbadey.demo.dto.response.ChapterResponseDto;
import com.vladbadey.demo.entity.Chapter;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-29T18:23:49+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Oracle Corporation)"
)
@Component
public class ChapterMapperImpl extends ChapterMapper {

    @Override
    public ChapterResponseDto toResponseDto(Chapter chapter) {
        if ( chapter == null ) {
            return null;
        }

        ChapterResponseDto chapterResponseDto = new ChapterResponseDto();

        chapterResponseDto.setId( chapter.getId() );
        chapterResponseDto.setName( chapter.getName() );
        chapterResponseDto.setContent( chapter.getContent() );

        return chapterResponseDto;
    }

    @Override
    public Chapter toEntity(ChapterRequestDto chapterRequestDto) {
        if ( chapterRequestDto == null ) {
            return null;
        }

        Chapter chapter = new Chapter();

        chapter.setName( chapterRequestDto.getName() );
        chapter.setContent( chapterRequestDto.getContent() );

        return chapter;
    }

    @Override
    public void updateChapter(ChapterRequestDto chapterDto, Chapter chapter) {
        if ( chapterDto == null ) {
            return;
        }

        chapter.setName( chapterDto.getName() );
        chapter.setContent( chapterDto.getContent() );
    }
}
