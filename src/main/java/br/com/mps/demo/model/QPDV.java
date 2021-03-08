package br.com.mps.demo.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;

import org.springframework.data.mongodb.core.QGeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.QGeoJsonPoint;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;

/**
 * QPDV is a Querydsl query type for PDV
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPDV extends EntityPathBase<PDV> {

    private static final long serialVersionUID = 796638511L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPDV pDV = new QPDV("pDV");

    public final QGeoJsonPoint address;

    public final QGeoJsonMultiPolygon coverageArea;

    public final StringPath document = createString("document");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath ownerName = createString("ownerName");

    public final StringPath tradingName = createString("tradingName");

    public QPDV(String variable) {
        this(PDV.class, forVariable(variable), INITS);
    }

    public QPDV(Path<? extends PDV> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPDV(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPDV(PathMetadata metadata, PathInits inits) {
        this(PDV.class, metadata, inits);
    }

    public QPDV(Class<? extends PDV> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new QGeoJsonPoint(forProperty("address")) : null;
        this.coverageArea = inits.isInitialized("coverageArea") ? new QGeoJsonMultiPolygon(forProperty("coverageArea")) : null;
    }

}

