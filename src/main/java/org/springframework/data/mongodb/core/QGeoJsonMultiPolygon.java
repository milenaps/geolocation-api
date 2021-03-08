package org.springframework.data.mongodb.core;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.BeanPath;
import com.querydsl.core.types.dsl.ListPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.SimplePath;
import com.querydsl.core.types.dsl.StringPath;

/**
 * QGeoJsonMultiPolygon is a Querydsl query type for GeoJsonMultiPolygon
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QGeoJsonMultiPolygon extends BeanPath<GeoJsonMultiPolygon> {

    private static final long serialVersionUID = 1589607111L;

    public static final QGeoJsonMultiPolygon geoJsonMultiPolygon = new QGeoJsonMultiPolygon("geoJsonMultiPolygon");

    public final ListPath<GeoJsonPolygon, SimplePath<GeoJsonPolygon>> coordinates = this.<GeoJsonPolygon, SimplePath<GeoJsonPolygon>>createList("coordinates", GeoJsonPolygon.class, SimplePath.class, PathInits.DIRECT2);

    public final StringPath type = createString("type");

    public QGeoJsonMultiPolygon(String variable) {
        super(GeoJsonMultiPolygon.class, forVariable(variable));
    }

    public QGeoJsonMultiPolygon(Path<? extends GeoJsonMultiPolygon> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGeoJsonMultiPolygon(PathMetadata metadata) {
        super(GeoJsonMultiPolygon.class, metadata);
    }

}

