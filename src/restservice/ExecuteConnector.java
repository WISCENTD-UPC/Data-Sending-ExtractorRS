package restservice;

import wiscc.dhis2connector.servicecontroller.Main;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/extraction")
public class ExecuteConnector {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String startExtraction() {
		return Integer.toString(Main.execute());
	}
}