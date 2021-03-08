package br.com.mps.demo.service;

import java.util.List;
import java.util.Optional;

import br.com.mps.demo.model.PDV;
import br.com.mps.demo.model.PDVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

@Service
public class PDVService {

	@Autowired
    private PDVRepository repository;

	@Autowired
	private MongoTemplate mongoTemplate;

    public PDV createOrUpdate(PDV pdv) {
		return repository.save(pdv);
    }

    public Optional<PDV> find(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Iterable<PDV> findAll(Predicate predicate) {
    	if (predicate == null) {
    		return repository.findAll();
    	}
    	return repository.findAll(predicate);
    }

	public List<PDV> findAll(Double lat, Double lng) {
		if (lat == null || lng == null) {
			return repository.findAll();
		}
		GeoJsonPoint point = new GeoJsonPoint(new Point(lat, lng));
		Query query = new Query();
		query.addCriteria(Criteria.where("coverageArea").intersects(point));

		return mongoTemplate.find(query, PDV.class);
	}

    public Page<PDV> findWithPagination(Predicate predicate, Pageable pageable) {
    	if (predicate == null) {
    		return repository.findAll(pageable);
    	}
        return repository.findAll(predicate, pageable);
    }
}