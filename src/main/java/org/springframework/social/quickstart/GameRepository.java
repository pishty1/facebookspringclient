package org.springframework.social.quickstart;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by pishty on 13/09/14.
 */
public interface GameRepository extends PagingAndSortingRepository<Game, Long> {

}
