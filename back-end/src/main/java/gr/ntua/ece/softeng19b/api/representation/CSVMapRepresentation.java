package gr.ntua.ece.softeng19b.api.representation;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.restlet.data.MediaType;
import org.restlet.representation.WriterRepresentation;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * A generic CSV representation of a java.util.Map object.
 */
public class CSVMapRepresentation extends WriterRepresentation {

    private final Map map;

    public CSVMapRepresentation(Map map) {
        super(MediaType.TEXT_CSV);
        this.map = map;
    }

    @Override
    public void write(Writer writer) throws IOException {
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
        csvPrinter.printRecord(map/*StringUtils.join(map)*/);
    }
}
