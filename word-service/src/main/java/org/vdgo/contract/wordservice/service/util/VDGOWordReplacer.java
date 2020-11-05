package org.vdgo.contract.wordservice.service.util;

import org.docx4j.Docx4J;
import org.docx4j.model.datastorage.BindingHandler;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.stereotype.Component;
import org.vdgo.contract.model.VDGODocument;

import java.io.*;

@Component
public class VDGOWordReplacer {

    public void replaceXmlBindings(String docPath, VDGODocument docData) throws FileNotFoundException, Docx4JException {
//        VDGOXmlUtil.convertToByteXml(docData, VDGODocument.class);
        final File docFile = new File(docPath);
        final String folderPath = docFile.getParent();
        final WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(new FileInputStream(docFile));
        InputStream xmlStream = new ByteArrayInputStream(VDGOXmlUtil.convertToByteXml(docData, VDGODocument.class));

        BindingHandler.getHyperlinkResolver().setHyperlinkStyle("Hyperlink");
        Docx4J.bind(wordMLPackage, xmlStream, Docx4J.FLAG_BIND_INSERT_XML | Docx4J.FLAG_BIND_BIND_XML); //Docx4J.FLAG_BIND_REMOVE_SDT
        Docx4J.save(wordMLPackage, new File(folderPath + File.separator + "Договор_" + "Something" + ".docx"), Docx4J.FLAG_NONE);
    }
}
