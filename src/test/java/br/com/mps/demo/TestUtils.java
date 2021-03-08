package br.com.mps.demo;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import br.com.mps.demo.model.PDV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {

	private static final Logger logger = LoggerFactory.getLogger(TestUtils.class);

	private static PDV pdv;
	private static List<PDV> pdvs;

	public static PDV getPDV() {
		if (pdv != null) {
			return pdv;
		}
		pdv = new PDV();
		pdv.setId("1");
		pdv.setTradingName("Trading Name");
		pdv.setOwnerName("Owner Name");
		pdv.setDocument("01234567/0001-00");
		pdv.setAddress(new GeoJsonPoint(-46.57421, -21.785741));

		return pdv;
	}

	@SuppressWarnings("unchecked")
	public static List<PDV> getMultiplePDVs() {
		if (pdvs != null) {
			return pdvs;
		}
		InputStream inputStream = TestUtils.class.getResourceAsStream("pdvs.json");

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		try {
			Map<Object, Object> result = mapper.readValue(inputStream, Map.class);
			pdvs = (List<PDV>) result.get("pdvs");
		} catch (Exception e) {
			logger.error("Could not fetch PDVs from json", e);
		}
		return pdvs;
	}
}