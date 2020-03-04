package io.music.playlist.service.mapper;

import com.wrapper.spotify.model_objects.specification.Track;
import io.music.playlist.service.dto.TrackDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TrackMapperImpl implements EntityMapper<TrackDTO, Track>  {
    @Override
    public Track toEntity(TrackDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Track.Builder builder = new Track.Builder();

        builder.setId(dto.getId());
        builder.setName(dto.getName());
        builder.setDiscNumber(dto.getDurationMs());
        builder.setHref(dto.getHref());

        return builder.build();
    }

    @Override
    public TrackDTO toDto(Track entity) {
        if ( entity == null ) {
            return null;
        }

        TrackDTO trackDTO = new TrackDTO();

        trackDTO.setId(entity.getId());
        trackDTO.setName(entity.getName());
        trackDTO.setDurationMs(entity.getDurationMs());
        trackDTO.setHref(entity.getHref());

        return trackDTO;
    }

    @Override
    public List<Track> toEntity(List<TrackDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Track> list = new ArrayList<Track>( dtoList.size() );
        for ( TrackDTO trackDTO : dtoList ) {
            list.add( toEntity( trackDTO ) );
        }

        return list;
    }

    @Override
    public List<TrackDTO> toDto(List<Track> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<TrackDTO> list = new ArrayList<TrackDTO>( entityList.size() );
        for ( Track track : entityList ) {
            list.add( toDto( track ) );
        }

        return list;
    }
}
