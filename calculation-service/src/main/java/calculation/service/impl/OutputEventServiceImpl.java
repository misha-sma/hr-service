package calculation.service.impl;

import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import calculation.data.event.CalculationCompletedEvent;
import calculation.service.OutputEventService;

@Service
public class OutputEventServiceImpl implements OutputEventService {

	@Override
	@CachePut(value = "output_event", key = "#event.eventId")
	public CalculationCompletedEvent saveEvent(CalculationCompletedEvent event) {
		return event;
	}
}
