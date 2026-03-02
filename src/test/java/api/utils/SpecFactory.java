package api.utils;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.lessThan;

public class SpecFactory {

	private static RequestSpecification REQ;
	private static ResponseSpecification RES;
	private InputStream is;

	private SpecFactory() {
	}

	public static RequestSpecification specBuilder(String baseFile) {
		ApiConfigReader cfg = new ApiConfigReader(baseFile);

		if (REQ == null) {
			String mockBase = System.getProperty("mock.baseUri");

			boolean isMock = (mockBase != null && !mockBase.trim().isEmpty());

			String baseUri = isMock ? mockBase : cfg.getProperty("api.baseUri");
			String basePath = isMock ? "" : cfg.getOptionalProperty("api.basePath");

			RequestSpecBuilder builder = new RequestSpecBuilder().setBaseUri(baseUri) // required
					.setContentType(ContentType.JSON).setAccept(ContentType.JSON)
			// .addHeader("authorization","Bearer "+ token) better architecture
			;

			// optional basePath
			if (basePath != null) {
				builder.setBasePath(basePath);
			}
		
		REQ = builder.build();
	}return REQ;

	}

	public static ResponseSpecification responseSpec() {
		if (RES == null) {
			RES = new ResponseSpecBuilder().expectContentType("application/json").expectResponseTime(lessThan(2000L))
					.build();

		}
		return RES;

	}
}
