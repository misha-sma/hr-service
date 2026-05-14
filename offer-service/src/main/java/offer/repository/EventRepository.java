package offer.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import offer.data.event.CalculationCompletedEvent;

@Repository
public interface EventRepository extends MongoRepository<CalculationCompletedEvent, UUID> {

}
