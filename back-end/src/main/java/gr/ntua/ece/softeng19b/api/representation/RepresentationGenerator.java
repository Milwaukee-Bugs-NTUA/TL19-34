package gr.ntua.ece.softeng19b.api.representation;

import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificMonth;
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificYear;
import org.restlet.representation.Representation;

import java.util.List;

public interface RepresentationGenerator {

    Representation generateRepresentationATLFSD(List<ATLRecordForSpecificDay> result);
    Representation generateRepresentationATLFSM(List<ATLRecordForSpecificMonth> result);
    Representation generateRepresentationATLFSY(List<ATLRecordForSpecificYear> result);
}
