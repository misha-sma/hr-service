package offer.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import offer.entity.Offer;

@Repository
public interface OfferRepository extends MongoRepository<Offer, UUID> {

}
