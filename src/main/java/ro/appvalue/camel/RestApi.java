package ro.appvalue.camel;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RestApi extends RouteBuilder {

	final String version = System.getenv("SRV_VERSION");
	
    @Override
    public void configure() {
        restConfiguration()
            .apiContextPath("/api-doc")
            .apiProperty("api.title", "Camel-SpringBoot REST API")
            .apiProperty("api.version", "1.0")
            .apiProperty("cors", "true")
            .apiContextRouteId("doc-api")
            .component("servlet");
//            .bindingMode(RestBindingMode.json);

        rest().description("Users REST service")
        .get("/message").description("User details")
            .route().routeId("message-get-api")
            .setHeader(Exchange.HTTP_METHOD, constant("GET"))
            .log("Version service: " + version)
            .setBody().constant(version);
    }
    
}
