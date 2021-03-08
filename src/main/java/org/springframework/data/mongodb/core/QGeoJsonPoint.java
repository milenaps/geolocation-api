package org.springframework.data.mongodb.core;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.BeanPath;
import com.querydsl.core.types.dsl.ListPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;

/**
 * QGeoJsonPoint is a Querydsl query type for GeoJsonPoint
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QGeoJsonPoint extends BeanPath<GeoJsonPoint> {

    private static final long serialVersionUID = 880102218L;

    public static final QGeoJsonPoint geoJsonPoint = new QGeoJsonPoint("geoJsonPoint");

    public final org.springframework.data.geo.QPoint _super = new org.springframework.data.geo.QPoint(this);

    public final ListPath<Double, NumberPath<Double>> coordinates = this.<Double, NumberPath<Double>>createList("coordinates", Double.class, NumberPath.class, PathInits.DIRECT2);

    public final StringPath type = createString("type");

    //inherited
    public final NumberPath<Double> x = _super.x;

    //inherited
    public final NumberPath<Double> y = _super.y;

    public QGeoJsonPoint(String variable) {
        super(GeoJsonPoint.class, forVariable(variable));
    }

    public QGeoJsonPoint(Path<? extends GeoJsonPoint> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGeoJsonPoint(PathMetadata metadata) {
        super(GeoJsonPoint.class, metadata);
    }

}

