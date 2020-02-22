package gr.ntua.ece.softeng19b.api.representation;

import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.DATLFRecordForSpecificDay;
import org.restlet.representation.Representation;

import java.util.List;

public interface RepresentationGenerator {

    Representation generateRepresentation(List<ATLRecordForSpecificDay> result);
    Representation generateRepresentationDATLFRFSD(List<DATLFRecordForSpecificDay> result);
    Representation generateRepresentationDATLFRFSM(List<DATLFRecordForSpecificMonth> result);
    //Representation generateRepresentationDATLFRFSY(List<DATLFRecordForSpecificDay> result);

}
