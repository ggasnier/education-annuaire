package com.guillaumegasnier.education.shell.services.impl;

import com.guillaumegasnier.education.shell.datasets.formations.CPFFormationDataset;
import com.guillaumegasnier.education.shell.datasets.lheo.LheoSubtype;
import com.guillaumegasnier.education.shell.services.ShellFormationService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.util.List;

@Slf4j
@Service
public class ShellFormationServiceImpl implements ShellFormationService {

    @Override
    public String createOrUpdateFormationsCpf(@NonNull List<CPFFormationDataset> datasets) {

        datasets.forEach(dataset -> {
            log.info(dataset.toString());
        });

        return String.format("Import terminé : %d formations(s) CPF enregistrée(s).", datasets.size());
    }

    @Override
    public String createOrUpdateFormationsOnisepIdf() {

        try {
            JAXBContext context = JAXBContext.newInstance(LheoSubtype.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            JAXBElement<LheoSubtype> jaxbElement = unmarshaller.unmarshal(new StreamSource(new File("datasets/lheo_action_IDF.xml")), LheoSubtype.class);
            LheoSubtype lheoSubtype = jaxbElement.getValue();

            lheoSubtype.getOffres().getFormation().forEach(formation -> {

                if (formation.getNumero().startsWith("FOR.389")) {
                    log.info("{} {} {}", formation.getNumero(), formation.getIntituleFormation().getValue(), formation.getContactFormation().getFirst().getCoordonnees().getAdresse().getDenomination().getValue());
                }
            });

            return String.format("Import terminé : %d formations(s) ONISEP enregistrée(s).", lheoSubtype.getOffres().getFormation().size());
        } catch (JAXBException e) {
            return String.format(e.getMessage(), e.getCause().getMessage());
        }

    }
}
