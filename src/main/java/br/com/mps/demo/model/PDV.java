package br.com.mps.demo.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pdvs")
public class PDV implements Serializable {

	private static final long serialVersionUID = -8468173478526023571L;
	@Id
	private String id;
	@NotNull
	private String tradingName;
	@NotNull
	private String ownerName;
	@NotNull
	@Indexed(unique=true)
	private String document;
	@NotNull
	private GeoJsonMultiPolygon coverageArea;
	@NotNull
	private GeoJsonPoint address;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTradingName() {
		return tradingName;
	}

	public void setTradingName(String tradingName) {
		this.tradingName = tradingName;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public GeoJsonMultiPolygon getCoverageArea() {
		return coverageArea;
	}

	public void setCoverageArea(GeoJsonMultiPolygon coverageArea) {
		this.coverageArea = coverageArea;
	}

	public GeoJsonPoint getAddress() {
		return address;
	}

	public void setAddress(GeoJsonPoint address) {
		this.address = address;
	}

}