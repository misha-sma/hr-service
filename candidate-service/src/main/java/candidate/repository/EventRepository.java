package candidate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import candidate.data.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

	@Query("SELECT e FROM Event e WHERE e.isSent=false ORDER BY e.createDate")
	List<Event> getEventsToSending();
}
