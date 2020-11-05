package org.vdgo.contract.wordservice.service;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vdgo.contract.wordservice.service.util.VDGOWordReplacer;

import java.io.FileNotFoundException;

import static org.vdgo.contract.wordservice.service.MockData.DOCUMENT;
import static org.vdgo.contract.wordservice.service.MockData.DOC_PATH;

@Component
public class WordService {
    @Autowired
    private VDGOWordReplacer replacer;

    public void doSomething() throws FileNotFoundException, Docx4JException {
        replacer.replaceXmlBindings(DOC_PATH, DOCUMENT);
    }
}
