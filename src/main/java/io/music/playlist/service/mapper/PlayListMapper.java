package io.music.playlist.service.mapper;

import io.music.playlist.domain.PlayList;
import io.music.playlist.service.dto.PlayListDTO;
import org.mapstruct.Mapper;

/**
 * @author <a href="mailto:alirezamardani@gmail.com">Alireza Mardani</a>
 * @version 0.0.1
 */

@Mapper(componentModel = "spring", uses = {PlayListItemMapper.class})
public interface PlayListMapper extends EntityMapper<PlayListDTO, PlayList> {

    default PlayList fromId(Long id) {
        if (id == null) {
            return null;
        }
        PlayList playList = new PlayList();
        playList.setId(id);
        return playList;
    }
}
