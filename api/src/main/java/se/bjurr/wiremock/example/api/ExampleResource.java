package se.bjurr.wiremock.example.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import se.bjurr.wiremock.example.api.dto.ItemDTO;

@Path("/")
public interface ExampleResource {
  @GET
  @Path("/get")
  @Produces(MediaType.APPLICATION_JSON)
  public ItemDTO getItem(@QueryParam("filter1") String filter1);

  @POST
  @Path("/create")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public void createItem(ItemDTO item);
}
