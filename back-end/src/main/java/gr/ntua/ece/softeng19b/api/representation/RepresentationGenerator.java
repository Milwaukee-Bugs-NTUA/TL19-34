package gr.ntua.ece.softeng19b.api.representation;

import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificMonth;
import org.restlet.representation.Representation;

import java.util.List;

public interface RepresentationGenerator {

    Representation generateRepresentation(List<ATLRecordForSpecificDay> result);
    Representation generateRepresentation1(List<ATLRecordForSpecificMonth> result);

}
