package br.com.mps.demo.api;

import br.com.mps.demo.TestUtils;
import br.com.mps.demo.model.PDV;
import br.com.mps.demo.service.PDVService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@WebMvcTest(PDVResource.class)
@AutoConfigureMockMvc
public class PDVResourceITest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private PDVService service;

	@Test
	public void shouldCreatePDV() throws Exception {

		PDV pdv = TestUtils.getPDV();
		//Populating pending required fields
		List<GeoJsonPolygon> polygons = new ArrayList<>(0);
		List<Point> points = new ArrayList<>(0);
		points.add(new Point(1, -1));
		points.add(new Point(2, -2));
		GeoJsonPolygon polygon = new GeoJsonPolygon(points);
		polygons.add(polygon);
		pdv.setCoverageArea(new GeoJsonMultiPolygon(polygons));

		when(service.createOrUpdate(pdv)).thenReturn(pdv);

		mvc.perform(post("/pdvs")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(pdv)))//FIXME Giving error during json parse, verify why and apply fix.
			.andDo(print())
			.andExpect(status().isCreated());
	}

	@Test
	public void shouldFailToCreatePDV() throws Exception {

		mvc.perform(post("/pdvs")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(TestUtils.getPDV())))
			.andDo(print())
			.andExpect(status().isBadRequest());
	}

	@Test
	public void shouldFindById() throws Exception {

		when(service.find(1l)).thenReturn(Optional.of(TestUtils.getPDV()));

		mvc.perform(get("/pdvs/{id}", 1l))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is("1")));
	}

	@Test
	public void shouldListAll() throws Exception {

		when(service.findAll(null, null)).thenReturn(TestUtils.getMultiplePDVs());

		mvc.perform(get("/pdvs"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(51)))
			.andExpect(jsonPath("$[0].id", is("1")));
	}

	@Test
	public void shouldFindByGeoLocation() throws Exception {
		List<PDV> pdvs = new ArrayList<PDV>(0);
		pdvs.add(TestUtils.getPDV());

		when(service.findAll(38.8895, -77.0353)).thenReturn(pdvs);

		mvc.perform(get("/pdvs?lat=38.8895&lng=-77.0353"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(1)));
	}
}