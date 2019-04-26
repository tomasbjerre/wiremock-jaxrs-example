package se.bjurr.wiremock.example.api.dto;

public class ItemDTO {
  private final String attr1;

  public ItemDTO(final String attr1) {
    this.attr1 = attr1;
  }

  public String getAttr1() {
    return attr1;
  }

  @Override
  public String toString() {
    return "ItemDTO [attr1=" + attr1 + "]";
  }
}
