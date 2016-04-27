package org.jboss.gravel.data.converter;

import java.lang.reflect.Method;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 *
 */
public final class EnumStringConverter<E extends Enum<E>> implements Converter {

    private final Map<E, String> eToString;
    private final Map<String, E> stringToE;

    @SuppressWarnings ({"unchecked"})
    public EnumStringConverter(final Class<E> enumClass) {
        if (! enumClass.isEnum()) {
            throw new FacesException("Unable to create converter; given type is not an enum type");
        }
        try {
            final Method method = enumClass.getMethod("values", new Class[0]);
            final E[] values = (E[]) method.invoke(new Object[0]);
            eToString = new EnumMap<E, String>(enumClass);
            stringToE = new HashMap<String, E>(values.length);
            for (E value : values) {
                final String s = value.toString();
                eToString.put(value, s);
                stringToE.put(s, value);
            }
        } catch (Exception e) {
            final FacesException ex = new FacesException("Unable to create converter; locating Enum values failed: " + e.getMessage());
            ex.setStackTrace(e.getStackTrace());
            throw ex;
        }

    }

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        final E rv = stringToE.get(value);
        if (rv == null) {
            throw new ConverterException(
                new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Conversion failed",
                    "Invalid value '" + value + "' given"
                )
            );
        }
        return rv;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        final String string = eToString.get(value);
        if (string == null) {
            throw new ConverterException(
                new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Conversion failed",
                    "Invalid value '" + value + "' given"
                )
            );
        }
        return string;
    }
}
