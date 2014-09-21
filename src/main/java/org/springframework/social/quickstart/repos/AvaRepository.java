package org.springframework.social.quickstart.repos;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.social.quickstart.model.Availability;

/**
 * Created by pishty on 13/09/14.
 */
public interface AvaRepository extends PagingAndSortingRepository<Availability, Long> {

}
