package nl.kaninefatendreef.si.server.repository;

import java.io.Serializable;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface AbstractSiDirectoryRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {
	

	

}
