package org.vdgo.contract.wordservice.service.word;

import org.docx4j.finders.TableFinder;
import org.docx4j.wml.Tbl;

import java.util.List;

public class CustomTblFinder extends TableFinder {
    private String ID;

    public CustomTblFinder(String ID) {
        this.ID = "ID:" + ID;
    }

    @Override
    public List<Object> apply(Object o) {
        if (o instanceof Tbl) {
            Tbl currTbl = (Tbl) o;
            if (currTbl.getTblPr().getTblCaption().getVal().equals(ID)) {
                tblList.add((Tbl) o);
            }
        }
        return null;
    }
}