package offer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import offer.data.entity.Offer;

public interface OfferRepository extends MongoRepository<Offer, Integer> {

}
