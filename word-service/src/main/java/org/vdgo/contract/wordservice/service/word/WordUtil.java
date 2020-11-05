package org.vdgo.contract.wordservice.service.word;

import org.docx4j.TraversalUtil;
import org.docx4j.XmlUtils;
import org.docx4j.finders.TableFinder;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.*;

import java.util.List;

public class WordUtil {
    private final P newLine = createParagraphWithText("");
    private MainDocumentPart mainDocumentPart;

    public WordUtil(MainDocumentPart mainDocumentPart) {
        this.mainDocumentPart = mainDocumentPart;
    }

    public void copyAndInsertTableAfter(String ID, Integer subId) {
        final TableFinder finder = new CustomTblFinder(ID);
        final List<Object> content = mainDocumentPart.getContent();
        TraversalUtil.visit(content, finder);
        final Tbl tbl = finder.tblList.get(0);

        final Tbl tblCopy = XmlUtils.deepCopy(tbl);
        final CTString ctString = new CTString();
        ctString.setVal(String.format("ID:%s-%d", ID, subId));
        tblCopy.getTblPr().setTblCaption(ctString);

        final int firstTablIndex = content.indexOf(tbl);
        content.add(firstTablIndex + 1, newLine);
        content.add(firstTablIndex + 2, tblCopy);
    }

    public P createParagraphWithText(String text) {
        ObjectFactory factory = Context.getWmlObjectFactory();
        P p = factory.createP();
        R r = factory.createR();
        Text t = factory.createText();
        t.setValue(text);
        r.getContent().add(t);
        p.getContent().add(r);
        return p;
    }
}
