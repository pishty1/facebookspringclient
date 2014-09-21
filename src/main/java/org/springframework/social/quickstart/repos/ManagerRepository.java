package org.springframework.social.quickstart.repos;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.social.quickstart.model.Manager;

/**
 * Created by pishty on 19/09/14.
 */
public interface ManagerRepository extends PagingAndSortingRepository<Manager, Long> {

}
