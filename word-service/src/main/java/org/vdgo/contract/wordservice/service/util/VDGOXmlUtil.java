package org.vdgo.contract.wordservice.service.util;

import lombok.SneakyThrows;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;

public class VDGOXmlUtil {
    @SneakyThrows
    public static <T> byte[] convertToByteXml(T object, Class<T> clazz) {
        final JAXBContext context = JAXBContext.newInstance(clazz);
        ByteArrayOutputStream xmlStream = new ByteArrayOutputStream();
        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(object, xmlStream);
        return xmlStream.toByteArray();
    }
}
