package io.music.playlist.service.mapper;

import io.music.playlist.domain.PlayListItem;
import io.music.playlist.service.dto.PlayListItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author <a href="mailto:alirezamardani@gmail.com">Alireza Mardani</a>
 * @version 0.0.1
 */

@Mapper(componentModel = "spring", uses = {PlayListMapper.class})
public interface PlayListItemMapper extends EntityMapper<PlayListItemDTO, PlayListItem> {

    @Override
    @Mapping(source = "playListId", target = "playList.id")
    PlayListItem toEntity(PlayListItemDTO dto);

    @Override
    @Mapping(target = "playListId", source = "playList.id")
    PlayListItemDTO toDto(PlayListItem entity);

    default PlayListItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        PlayListItem playListItem = new PlayListItem();
        playListItem.setId(id);
        return playListItem;
    }
}
