package calculation.service.impl;

import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import calculation.data.entity.Metric;
import calculation.service.MetricService;

@Service
public class MetricServiceImpl implements MetricService {

	@Override
	@CachePut(value = "metric", key = "#metric.candidateId")
	public Metric saveMetric(Metric metric) {
		return metric;
	}
}
