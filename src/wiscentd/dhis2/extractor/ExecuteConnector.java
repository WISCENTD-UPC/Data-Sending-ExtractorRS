package wiscentd.dhis2.extractor;

import wiscentd.dhis2.connector.Main;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/extraction")
public class ExecuteConnector {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String startExtraction() {
        new Main().executeExtraction();
        return "OK"; // TODO
    }
}