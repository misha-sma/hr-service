package calculation.service.impl;

import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import calculation.data.entity.Metric;
import calculation.service.MetricService;

@Service
public class MetricServiceImpl implements MetricService {

	@CachePut(value = "metric", key = "#id")
	@Override
	public Metric saveMetric(Metric metric, Integer id) {
		return metric;
	}
}
