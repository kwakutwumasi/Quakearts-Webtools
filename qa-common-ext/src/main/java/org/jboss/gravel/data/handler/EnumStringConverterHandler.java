package org.jboss.gravel.data.handler;

import java.io.IOException;
import org.jboss.gravel.common.annotation.TldAttribute;
import org.jboss.gravel.common.annotation.TldTag;
import org.jboss.gravel.data.converter.EnumStringConverter;

import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagException;
import javax.faces.view.facelets.TagHandler;

import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.component.ValueHolder;
import javax.faces.convert.Converter;

/**
 *
 */
@TldTag (
    name = "enumStringConverter",
    description = "Register a converter for Enum types on the enclosing value holder.  This converter differs from " +
        "the stock Enum converter in that this converter uses the result of the toString() method on each value to " +
        "determine the string value, as opposed to using the value name.",
    attributes = {
        @TldAttribute (
            name = "type",
            description = "An EL expression which resolves to a Class instance or a name of a Class that is derived " +
                "from <code>java.lang.Enum</code>.",
            required = true,
            deferredType = Object.class
        )
    }
)
public final class EnumStringConverterHandler extends TagHandler {
    private final TagAttribute typeAttribute;

    public EnumStringConverterHandler(final TagConfig config) {
        super(config);
        typeAttribute = getRequiredAttribute("type");
    }

    @SuppressWarnings ({"unchecked"})
    public void apply(FaceletContext ctx, UIComponent parent) throws IOException, FacesException, ELException {
        try {
            if (!(parent instanceof ValueHolder)) {
                throw new TagException(tag, "Parent component is not a ValueHolder");
            }
            final ValueHolder valueHolder = (ValueHolder) parent;
            final Converter converter;
            final ValueExpression typeExpression = typeAttribute.getValueExpression(ctx, Class.class);
            final Object typeValue = typeExpression.getValue(ctx);
            final Class<? extends Enum> type;
            if (typeValue instanceof Class) {
                type = (Class<? extends Enum>) typeValue;
            } else if (typeValue != null) {
                try {
                    type = (Class<? extends Enum>) Class.forName(typeValue.toString());
                } catch (ClassNotFoundException e) {
                    final TagException ex = new TagException(tag, "Cannot initialize converter for type " + typeValue + ": " + e.getMessage());
                    ex.setStackTrace(e.getStackTrace());
                    throw ex;
                }
            } else {
                throw new TagException(tag, "Value of 'type' attribute resolved to null");
            }
            converter = converter(type);
            valueHolder.setConverter(converter);
        } catch (TagException tex) {
            throw tex;
        } catch (RuntimeException rex) {
            TagException tex = new TagException(tag, "An exception of type " + rex.getClass().getName() + " occurred: " + rex.getMessage());
            tex.setStackTrace(rex.getStackTrace());
            throw tex;
        }
    }

    private <T extends Enum<T>> EnumStringConverter<T> converter(Class<T> type) {
        return new EnumStringConverter<T>(type);
    }
}
