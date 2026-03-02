package api.hooks;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class ApiHooks {
	
	private static WireMockServer wireMock; 
	
	@Before("@wiremock")
	 public void startWireMock() {
        if (wireMock == null) {
            wireMock = new WireMockServer(
                    options()
                            .dynamicPort()
                            // ✅ classpath root: src/test/resources/wiremock
                            .usingFilesUnderClasspath("wiremock")
            );
            wireMock.start();

            System.setProperty("mock.baseUri", "http://localhost:" + wireMock.port());
            System.out.println("WireMock started at: " + System.getProperty("mock.baseUri"));
        }
    }
	
	@After("@wiremock")
    public void stopWireMock() {
        if (wireMock != null) {
            wireMock.stop();
            wireMock = null;
            System.clearProperty("mock.baseUri");
        }
    }
}
	

