package milos.davitkovic.utills.core.database.dao;


import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Interface for a DAO.
 *
 * @param <MODEL> the type of the source object
 */
@NoRepositoryBean
public interface AbstractDAO<MODEL> extends PagingAndSortingRepository<MODEL, Long> {
}
