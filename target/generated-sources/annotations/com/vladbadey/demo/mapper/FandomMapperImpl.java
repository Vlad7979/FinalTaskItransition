package com.vladbadey.demo.mapper;

import com.vladbadey.demo.dto.request.FandomRequestDto;
import com.vladbadey.demo.dto.response.FandomResponseDto;
import com.vladbadey.demo.entity.Fandom;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-29T18:23:49+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Oracle Corporation)"
)
@Component
public class FandomMapperImpl extends FandomMapper {

    @Override
    public FandomResponseDto toResponseDto(Fandom fandom) {
        if ( fandom == null ) {
            return null;
        }

        FandomResponseDto fandomResponseDto = new FandomResponseDto();

        fandomResponseDto.setName( fandom.getName() );
        fandomResponseDto.setImage( fandom.getImage() );

        return fandomResponseDto;
    }

    @Override
    public Fandom toEntity(FandomRequestDto fandomRequestDto) {
        if ( fandomRequestDto == null ) {
            return null;
        }

        Fandom fandom = new Fandom();

        fandom.setName( fandomRequestDto.getName() );
        fandom.setImage( fandomRequestDto.getImage() );

        return fandom;
    }
}
