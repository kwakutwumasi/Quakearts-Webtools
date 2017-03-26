package org.jboss.gravel.common.ui;

import org.jboss.gravel.common.annotation.TldAttribute;

import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;

/**
 *
 */
public interface Input {
    String getClientId(FacesContext facesContext);

    @TldAttribute (
        description = "The type of the target attribute.  Possible values are: <ul>" +
            "<li><code>stream</code> - the target value will be an <code>InputStream</code> instance, and any " +
            "temporary backing file created will be automatically cleaned up after the request is complete.</li>" +
            "<li><code>reader</code> - the target value will be a <code>Reader</code> instance, and any " +
            "temporary backing file created will be automatically cleaned up after the request is complete.  You " +
            "should also specify the <code>targetCharset</code> attribute when using this target type.  If no " +
            "charset is specified, then the platform's default charset is used.</li>" +
            "<li><code>string</code> - the target value will be converted to a <code>String</code> instance. No " +
            "backing file will be created; the full contents of the file will be stored in memory.  You should" +
            "also specify the <code>targetCharset</code> attribute when using this target type.  If no charset is " +
            "specified, then the platform's default charset is used.</li>" +
            "<li><code>bytes</code> - the target value will be a <code>byte</code> array.  No backing file " +
            "will be created; the full contents of the file will be stored in memory.</li>" +
            "<li><code>chars</code> - the target value will be a <code>char</code> array.  No backing file " +
            "will be created; the full contents of the file will be stored in memory.</li>" +
            "</ul>"
    )
    String getTargetType();

    @TldAttribute(
        description = "The charset to use when converting bytes into characters.  This attribute is only relevant " +
            "for target types that require characters, specifically <code>string</code>, <code>chars</code>, and <code>" +
            "reader</code>."
    )
    String getTargetCharset();

    @TldAttribute(
        name = "target",
        description = "An EL expression into which the file data will be stored for processing by actions.  By " +
            "default this will be an InputStream, but can be changed by providing the <code>targetType</code> attribute.",
        required = true,
        deferredType = Object.class
    )
    void updateTarget(Object targetValue) throws ELException, FacesException;

}
