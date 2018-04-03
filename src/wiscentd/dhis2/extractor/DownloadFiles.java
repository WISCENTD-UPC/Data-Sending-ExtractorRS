package wiscentd.dhis2.extractor;

import wiscentd.dhis2.connector.Main;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

@Path("/events")
public class DownloadFiles {
    @GET
    @Path("/estimated")
    public Response downloadEstimatedTemplate() {
        Properties config = getDhisConfig();
        java.nio.file.Path path = Paths.get(config.getProperty("filesPath"), config.getProperty("estimatedPath"));
        File file = new File(path.toString());
        ResponseBuilder response = Response.ok(file);
        response.header("Content-Disposition", "attachment;filename=estimated_template.xlsx");
        return response.build();
    }

    @GET
    @Path("/summary")
    public Response downloadSummary() {
        Properties config = getDhisConfig();
        java.nio.file.Path path = Paths.get(config.getProperty("inboxPath"), config.getProperty("eventsPath"));
        File file = new File(path.toString());
        ResponseBuilder response = Response.ok(file);
        response.header("Content-Disposition", "attachment;filename=data-summary.json");
        return response.build();
    }

    @GET
    @Path("/summaryCSV")
    public Response downloadCSVSummary() {
        Properties config = getDhisConfig();
        consultExtraction();
        java.nio.file.Path path = Paths.get(config.getProperty("inboxPath"), config.getProperty("eventsCSV"));
        File file = new File(path.toString());
        ResponseBuilder response = Response.ok(file);
        response.header("Content-Disposition", "attachment;filename=data-summary.csv");
        return response.build();
    }

    @GET
    @Path("/attachments")
    public Response downloadAttachments() {
        Properties config = getDhisConfig();
        java.nio.file.Path path = Paths.get(config.getProperty("inboxPath"), config.getProperty("attachmentsPath"));
        File file = new File(path.toString());
        ResponseBuilder response = Response.ok(file);
        response.header("Content-Disposition", "attachment;filename=data-attachments.tar.gz");
        return response.build();
    }

    private void consultExtraction() {
        new Main().consult();
    }

    private Properties getDhisConfig() {
        System.out.println("Reading properties");
        Properties prop = new Properties();
        Properties dhisAPIconfig = null;
        String propertiesFile = "config.properties";
        InputStream is = getClass().getClassLoader().getResourceAsStream(propertiesFile);
        if (is != null)
            try {
                prop.load(is);
                dhisAPIconfig = new Properties();
                InputStream input = getClass().getClassLoader().getResourceAsStream(prop.getProperty("dhisAPIconfig"));
                dhisAPIconfig.load(input);
                System.out.println("DhisApi Configuration files loaded correctly");

            } catch (IOException e) {
                e.printStackTrace();
            }
        return dhisAPIconfig;
    }
}