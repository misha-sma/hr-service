package calculation.service;

import calculation.data.entity.Metric;

public interface MetricService {

	Metric saveMetric(Metric metric, Integer id);
}
