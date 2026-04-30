package offer.service;

import offer.data.entity.MetricWithId;
import offer.data.entity.Offer;

public interface OfferService {

	void createOffer(MetricWithId metricWithId);

	Offer getOffer(Integer offerId);
}
