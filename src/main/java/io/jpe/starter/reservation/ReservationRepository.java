package io.jpe.starter.reservation;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends PagingAndSortingRepository <Reservation, Integer> {
	public List<Reservation> findAllByLocationId(int id);
	public List<Reservation> findAllByLocationId(int id, Pageable pageable);
}
