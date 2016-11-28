package br.com.fws.certificado_digital.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by nando on 28/11/16.
 */

@FacesConverter(forClass = LocalDate.class)
public class LocalDateConverter implements Converter {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return LocalDate.parse(value,formatter);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value == null) return null;

        LocalDate date = (LocalDate) value;

        return date.format(formatter);
    }
}
