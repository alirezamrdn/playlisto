package io.music.playlist.repository;

import io.music.playlist.domain.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author <a href="mailto:alirezamardani@gmail.com">Alireza Mardani</a>
 * @version 0.0.1
 */

@Repository
public interface PlayListRepository extends JpaRepository<PlayList, Long> {
}
