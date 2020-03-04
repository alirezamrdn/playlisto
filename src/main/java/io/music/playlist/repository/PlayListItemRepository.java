package io.music.playlist.repository;

import io.music.playlist.domain.PlayListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author <a href="mailto:alirezamardani@gmail.com">Alireza Mardani</a>
 * @version 0.0.1
 */

@Repository
public interface PlayListItemRepository extends JpaRepository<PlayListItem, Long> {

    @Transactional
    @Modifying
    @Query("delete from PlayListItem pi where pi.playList.id = :playlistId")
    void deleteAllPlayListItems(@Param("playlistId") Long playlistId);

    @Transactional
    @Modifying
    @Query("delete from PlayListItem pi where pi.playList.id = :playlistId and trackId = :trackId")
    void deleteByPlaylistIdAndtrackId(@Param("playlistId") Long playlistId, @Param("trackId") Long trackId);
}
