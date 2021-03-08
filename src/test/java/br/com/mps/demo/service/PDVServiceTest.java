package br.com.mps.demo.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import br.com.mps.demo.TestUtils;
import br.com.mps.demo.model.PDV;
import br.com.mps.demo.model.PDVRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class PDVServiceTest {

	@InjectMocks
	private PDVService service;

	@MockBean
	private PDVRepository repository;

	@MockBean
	private MongoTemplate mongoTemplate;

	@Test
	public void shouldFindByGeoLocation() {

		when(repository.findAll()).thenReturn(TestUtils.getMultiplePDVs());
		assertNotNull(service.findAll(null, null));

		GeoJsonPoint point = new GeoJsonPoint(new Point(43.36556, -22.99669));
		Query query = new Query();
		query.addCriteria(Criteria.where("coverageArea").intersects(point));
		when(mongoTemplate.find(query, PDV.class)).thenReturn(TestUtils.getMultiplePDVs());

		assertNotNull(service.findAll(43.36556, -22.99669));
	}
}