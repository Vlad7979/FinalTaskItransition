package com.vladbadey.demo.mapper;

import com.vladbadey.demo.dto.request.CompositionRequestDto;
import com.vladbadey.demo.dto.response.CompositionResponseDto;
import com.vladbadey.demo.entity.Composition;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-29T18:23:49+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Oracle Corporation)"
)
@Component
public class CompositionMapperImpl extends CompositionMapper {

    @Override
    public CompositionResponseDto toResponseDto(Composition composition) {
        if ( composition == null ) {
            return null;
        }

        CompositionResponseDto compositionResponseDto = new CompositionResponseDto();

        compositionResponseDto.setName( composition.getName() );
        compositionResponseDto.setDescription( composition.getDescription() );
        compositionResponseDto.setImage( composition.getImage() );

        return compositionResponseDto;
    }

    @Override
    public Composition toEntity(CompositionRequestDto compositionRequestDto) {
        if ( compositionRequestDto == null ) {
            return null;
        }

        Composition composition = new Composition();

        composition.setName( compositionRequestDto.getName() );
        composition.setDescription( compositionRequestDto.getDescription() );
        composition.setImage( compositionRequestDto.getImage() );

        return composition;
    }

    @Override
    public void updateComposition(CompositionRequestDto compositionRequestDto, Composition composition) {
        if ( compositionRequestDto == null ) {
            return;
        }

        composition.setName( compositionRequestDto.getName() );
        composition.setDescription( compositionRequestDto.getDescription() );
        composition.setImage( compositionRequestDto.getImage() );
    }
}
