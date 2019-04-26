package se.bjurr.wiremock.example.api.mock;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMockJaxrs.invocation;
import static javax.servlet.http.HttpServletResponse.SC_ACCEPTED;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.Options;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;
import org.junit.Test;
import se.bjurr.wiremock.example.api.ExampleResource;
import se.bjurr.wiremock.example.api.dto.ItemDTO;

public class CreateMocksTest {
  private static Logger LOG = Logger.getLogger(CreateMocksTest.class.getName());

  public static String getMappingsRootPath() throws URISyntaxException {
    final Path storagePath = Paths.get(Main.class.getResource("/root/mappings/.gitkeep").toURI());
    final Path mappingsRoot = storagePath.getParent().getParent();
    final String mappingsRootPath = mappingsRoot.toFile().getPath();
    LOG.info("Storing Wiremock mappings in: " + mappingsRootPath);
    return mappingsRootPath;
  }

  @Test
  public void createMocks() throws URISyntaxException {
    final String mappingsRootPath = CreateMocksTest.getMappingsRootPath();

    final Options options =
        new WireMockConfiguration() //
            .dynamicPort() //
            .withRootDirectory(mappingsRootPath);

    final WireMockServer wm = new WireMockServer(options);
    wm.start();
    WireMock.configureFor(wm.port());

    createStubs();

    wm.saveMappings();
  }

  private void createStubs() {

    /**
     * Try it out with:<bt>
     *
     * <pre>
     * curl -v -H "Accept: application/json" http://localhost:8080/get?filter1=abc
     * curl -v -H "Accept: application/json" http://localhost:8080/get?filter1=some+attr+value
     * </pre>
     */
    stubFor(
        invocation(ExampleResource.class, (r) -> r.getItem("abc")) //
            .willReturn(aResponse().withStatus(SC_NOT_FOUND)));

    for (final ItemDTO itemDto : MockFactory.getAllItems()) {
      stubFor(
          invocation(ExampleResource.class, (r) -> r.getItem(itemDto.getAttr1())) //
              .willReturn(aResponse().withStatus(SC_ACCEPTED), itemDto));

      stubFor(
          invocation(ExampleResource.class, (r) -> r.createItem(itemDto)) //
              .willReturn(aResponse().withStatus(SC_ACCEPTED)));
    }
  }
}
