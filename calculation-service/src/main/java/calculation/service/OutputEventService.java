package calculation.service;

import calculation.data.event.CalculationCompletedEvent;

public interface OutputEventService {

	CalculationCompletedEvent saveEvent(CalculationCompletedEvent event);
}
