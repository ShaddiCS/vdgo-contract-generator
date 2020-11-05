package org.vdgo.contract.wordservice.service;

import org.vdgo.contract.model.VDGODocument;

public class MockData {
    public static final String DOC_PATH = "F:\\Projects\\vdgo-contract-world\\docs\\Договор_ТО_ВДГО_НовгородаудитЭНЕРГО - Copy_2.docx";
    public static final VDGODocument DOCUMENT = new VDGODocument(
            "Филимоново",
            "Филимонов А.А.",
            "название",
            "Адресовно",
            "Января",
            "20.03.20",
            "33.44.20",
            "42",
            "+7-911-322-34-23",
            "some@mail.com",
            2
    );
}
