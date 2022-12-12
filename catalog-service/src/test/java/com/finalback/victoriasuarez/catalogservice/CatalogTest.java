//package com.finalback.victoriasuarez.catalogservice;
//
//import com.finalback.victoriasuarez.catalogservice.model.Catalog;
//import io.restassured.http.ContentType;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Tag;
//import org.junit.jupiter.api.Test;
//
//import static io.restassured.RestAssured.given;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class CatalogTest extends BaseAPI {
//
//    @Test
//    @Tag("Functional")
//    @DisplayName("Testear toda la aplicación con api gateway")
//    void testingAll() {
//
//        given().
//                contentType(ContentType.JSON).
//                body(
//                        new MovieRequest(198L, "movie new", "genre", "url")
//                ).
//                when().post("/movies");
//
//        given().
//                contentType(ContentType.JSON).
//                body(
//                        new SerieRequest(945L, "serie new", "genre")
//                ).
//                when().post("/series");
//
//        Catalog responseOnline = given()
//                .pathParams("genre", "genre")
//                .when().get("/catalogs/online/{genre}")
//                .as(Catalog.class);
//
//        Catalog responseOffline = given()
//                .pathParams("genre", "genre")
//                .when().get("/catalogs/offline/{genre}")
//                .as(Catalog.class);
//
//        assertEquals(responseOnline.getMovies().size(), 1);
//        assertEquals(responseOnline.getSeries().size(), 1);
//
//        assertEquals(responseOffline.getMovies().size(), 1);
//        assertEquals(responseOffline.getSeries().size(), 1);
//    }
//
//    @Getter
//    @Setter
//    @NoArgsConstructor
//    @AllArgsConstructor
//    public static class MovieRequest {
//        private Long id;
//        private String name;
//        private String genre;
//        private String urlStream;
//    }
//
//    @Getter
//    @Setter
//    @NoArgsConstructor
//    @AllArgsConstructor
//    public static class SerieRequest {
//        private Long id;
//        private String name;
//        private String genre;
//    }
//
//}
