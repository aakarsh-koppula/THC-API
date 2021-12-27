package io.jpe.starter.menuitem;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface MenuItemRepository extends PagingAndSortingRepository <MenuItem, Integer> {
	
	public List<MenuItem> findAllByLocationId(int id, Pageable pageable);
	public List<MenuItem> findAllByLocationId(int id);
}
