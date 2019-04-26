package se.bjurr.wiremock.example.api.mock;

import java.util.Arrays;
import java.util.List;
import se.bjurr.wiremock.example.api.dto.ItemDTO;

public class MockFactory {

  public static List<ItemDTO> getAllItems() {
    return Arrays.asList(new ItemDTO("some attr value"));
  }
}
