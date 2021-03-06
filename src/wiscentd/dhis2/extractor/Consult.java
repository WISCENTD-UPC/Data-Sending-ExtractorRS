package wiscentd.dhis2.extractor;

import wiscentd.dhis2.connector.Main;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/consult")
public class Consult {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String startConsult() {
        return new Main().executeConsult();
    }
}