package gr.ntua.ece.softeng19b.api.resource;

import org.restlet.data.Status;
import org.restlet.representation.Representation;
import gr.ntua.ece.softeng19b.api.representation.JsonMapRepresentation;
import org.restlet.data.MediaType;
import org.restlet.data.Form;
import org.restlet.representation.WriterRepresentation;
import org.restlet.resource.ResourceException;
import org.apache.commons.fileupload.*;
import java.util.Collections;

public class ImportActualTotalLoadDataSet extends EnergyResource {

    @Override
    protected Representation post(Representation entity) throws ResourceException {
        
        Form form = new Form(entity);
        String path = form.getFirstValue("file");
        String h = getRequest().getHeaders().toString();
        System.out.println("entity: "+ entity.getMediaType());
        System.out.println("what the fuck " + entity.getParameter("file"));
        System.out.println("path " + path); 
        System.out.println("header " + h);
        // Import dataset
        /*Representation result = null;
        if (entity != null) {
            if (MediaType.MULTIPART_FORM_DATA.equals(entity.getMediaType(), true)) {
                // 1/ Create a factory for disk-based file items
                DiskFileItemFactory factory = new DiskFileItemFactory();
                factory.setSizeThreshold(1000240);

                // 2/ Create a new file upload handler based on the Restlet
                // FileUpload extension that will parse Restlet requests and
                // generates FileItems.
                RestletFileUpload upload = new RestletFileUpload(factory);

                // 3/ Request is parsed by the handler which generates a
                // list of FileItems
                FileItemIterator fileIterator = upload.getItemIterator(entity);

                // Process only the uploaded item called "fileToUpload"
                // and return back
                boolean found = false;
                while (fileIterator.hasNext() && !found) {
                    FileItemStream fi = fileIterator.next();
                    if (fi.getFieldName().equals("fileToUpload")) {
                        found = true;
                        // consume the stream immediately, otherwise the stream
                        // will be closed.
                        StringBuilder sb = new StringBuilder("media type: ");
                        sb.append(fi.getContentType()).append("\n");
                        sb.append("file name : ");
                        sb.append(fi.getName()).append("\n");
                        BufferedReader br = new BufferedReader(
                                new InputStreamReader(fi.openStream()));
                        String line = null;
                        while ((line = br.readLine()) != null) {
                            sb.append(line);
                        }
                        sb.append("\n");
                        result = new StringRepresentation(sb.toString(), MediaType.TEXT_PLAIN);
                    }
                }
            } else {
                // POST request with no entity.
                setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
            }
        }
        return result;*/

        return new JsonMapRepresentation(Collections.emptyMap());
    }
}
