package se.bjurr.wiremock.example.api.mock;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.ClasspathFileSource;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.standalone.JsonFileMappingsSource;
import java.net.URISyntaxException;

public class Main {

  private static final int PORT = 8080;

  public static void main(final String[] args) throws URISyntaxException {
    final WireMockConfiguration wmConfig =
        wireMockConfig() //
            .port(PORT);
    final WireMockServer wireMockServer = new WireMockServer(wmConfig);
    wireMockServer.loadMappingsUsing(new JsonFileMappingsSource(new ClasspathFileSource("root")));

    printInfo(wireMockServer);

    wireMockServer.start();
  }

  private static void printInfo(final WireMockServer wireMockServer) {
    System.out.println();
    System.out.println();
    System.out.println(
        "    "
            + wireMockServer.listAllStubMappings().getMappings().size()
            + " mappings configured. See configuration at:");
    System.out.println();
    System.out.println("       http://localhost:" + PORT + "/__admin/");
    System.out.println();
    System.out.println();
  }
}
