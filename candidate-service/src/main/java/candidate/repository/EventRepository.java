package candidate.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import candidate.data.entity.Event;
import jakarta.persistence.LockModeType;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT e FROM Event e WHERE e.isSent=false ORDER BY e.createDate LIMIT 1")
	Optional<Event> getOneEventToSending();
}
