/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 * 
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License. You can obtain
 * a copy of the License at https://glassfish.dev.java.net/public/CDDL+GPL.html
 * or glassfish/bootstrap/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at glassfish/bootstrap/legal/LICENSE.txt.
 * Sun designates this particular file as subject to the "Classpath" exception
 * as provided by Sun in the GPL Version 2 section of the License file that
 * accompanied this code.  If applicable, add the following below the License
 * Header, with the fields enclosed by brackets [] replaced by your own
 * identifying information: "Portions Copyrighted [year]
 * [name of copyright owner]"
 * 
 * Contributor(s):
 * 
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package com.quakearts.webapp.facelets.bootstrap.renderkit;

import java.util.Map;

import com.quakearts.webapp.facelets.util.UtilityMethods;

import static com.quakearts.webapp.facelets.bootstrap.renderkit.Attribute.*;
import static com.quakearts.webapp.facelets.util.UtilityMethods.*;
/**
 * This class contains mappings between the standard components
 * and the passthrough attributes associated with them.
 */
public class AttributeManager {

    private static Map<String,Attribute[]> ATTRIBUTE_LOOKUP=UtilityMethods.<String,Attribute[]>map()
        .add("CommandButton",ar(
            attr("accesskey")
            ,attr("alt")
            ,attr("dir")
            ,attr("lang")
            ,attr("onblur","blur")
            ,attr("onchange","change")
            ,attr("ondblclick","dblclick")
            ,attr("onfocus","focus")
            ,attr("onkeydown","keydown")
            ,attr("onkeypress","keypress")
            ,attr("onkeyup","keyup")
            ,attr("onmousedown","mousedown")
            ,attr("onmousemove","mousemove")
            ,attr("onmouseout","mouseout")
            ,attr("onmouseover","mouseover")
            ,attr("onmouseup","mouseup")
            ,attr("onselect","select")
            ,attr("disabled")
            ,attr("style")
            ,attr("tabindex")
            ,attr("title")
        ))
        .add("FormForm",ar(
            attr("accept")
            ,attr("dir")
            ,attr("enctype")
            ,attr("lang")
            ,attr("onclick","click")
            ,attr("ondblclick","dblclick")
            ,attr("onkeydown","keydown")
            ,attr("onkeypress","keypress")
            ,attr("onkeyup","keyup")
            ,attr("onmousedown","mousedown")
            ,attr("onmousemove","mousemove")
            ,attr("onmouseout","mouseout")
            ,attr("onmouseover","mouseover")
            ,attr("onmouseup","mouseup")
            ,attr("onreset")
            ,attr("onsubmit")
            ,attr("style")
            ,attr("target")
            ,attr("title")
        ))
        .add("InputText",ar(
            attr("accesskey")
            ,attr("alt")
            ,attr("dir")
            ,attr("lang")
            ,attr("maxlength")
            ,attr("onblur","blur")
            ,attr("onclick","click")
            ,attr("ondblclick","dblclick")
            ,attr("onfocus","focus")
            ,attr("onkeydown","keydown")
            ,attr("onkeypress","keypress")
            ,attr("onkeyup","keyup")
            ,attr("onmousedown","mousedown")
            ,attr("onmousemove","mousemove")
            ,attr("onmouseout","mouseout")
            ,attr("onmouseover","mouseover")
            ,attr("onmouseup","mouseup")
            ,attr("onselect","select")
            ,attr("disabled")
            ,attr("size")
            ,attr("tabindex")
            ,attr("title")
        ))
        .add("MessagesMessages",ar(
            attr("dir")
            ,attr("lang")
            ,attr("style")
            ,attr("title")
        ))
        .add("SelectBooleanCheckbox",ar(
            attr("accesskey")
            ,attr("dir")
            ,attr("lang")
            ,attr("onblur","blur")
            ,attr("onchange","change")
            ,attr("ondblclick","dblclick")
            ,attr("onfocus","focus")
            ,attr("onkeydown","keydown")
            ,attr("onkeypress","keypress")
            ,attr("onkeyup","keyup")
            ,attr("onmousedown","mousedown")
            ,attr("onmousemove","mousemove")
            ,attr("onmouseout","mouseout")
            ,attr("onmouseover","mouseover")
            ,attr("onmouseup","mouseup")
            ,attr("onselect","select")
            ,attr("disabled")
            ,attr("style")
            ,attr("tabindex")
            ,attr("title")
        ))
        .add("SelectManyMenu",ar(
            attr("accesskey")
            ,attr("dir")
            ,attr("lang")
            ,attr("onblur","blur")
            ,attr("onclick","click")
            ,attr("ondblclick","dblclick")
            ,attr("onfocus","focus")
            ,attr("onmousedown","mousedown")
            ,attr("onmousemove","mousemove")
            ,attr("onmouseout","mouseout")
            ,attr("onmouseover","mouseover")
            ,attr("onmouseup","mouseup")
            ,attr("onselect","select")
            ,attr("tabindex")
            ,attr("style")
            ,attr("title")
        ))
        .add("SelectInputGroup",ar(
                attr("accesskey")
                ,attr("dir")
                ,attr("lang")
                ,attr("onblur","blur")
                ,attr("onclick","click")
                ,attr("ondblclick","dblclick")
                ,attr("onfocus","focus")
                ,attr("onmousedown","mousedown")
                ,attr("onmousemove","mousemove")
                ,attr("onmouseout","mouseout")
                ,attr("onmouseover","mouseover")
                ,attr("onmouseup","mouseup")
                ,attr("onselect","select")
                ,attr("tabindex")
                ,attr("style")
                ,attr("title")
            ))
        .add("SelectManyList",ar(
                attr("accesskey")
                ,attr("dir")
                ,attr("lang")
                ,attr("onblur","blur")
                ,attr("onclick","click")
                ,attr("ondblclick","dblclick")
                ,attr("onfocus","focus")
                ,attr("onkeydown","keydown")
                ,attr("onkeypress","keypress")
                ,attr("onkeyup","keyup")
                ,attr("onmousedown","mousedown")
                ,attr("onmousemove","mousemove")
                ,attr("onmouseout","mouseout")
                ,attr("onmouseover","mouseover")
                ,attr("onmouseup","mouseup")
                ,attr("onselect","select")
                ,attr("style")
                ,attr("tabindex")
                ,attr("title")
        ))
        .add("BootNav",ar(attr("onclick","click")
            ,attr("ondblclick","dblclick")
            ,attr("onmousedown","mousedown")
            ,attr("onmousemove","mousemove")
            ,attr("onmouseout","mouseout")
            ,attr("onmouseover","mouseover")
            ,attr("onmouseup","mouseup")
            ,attr("style")
            ,attr("title")
        ))
        .add("DataTable",ar(
            attr("bgcolor")
            ,attr("border")
            ,attr("cellpadding")
            ,attr("cellspacing")
            ,attr("dir")
            ,attr("frame")
            ,attr("lang")
            ,attr("onclick","click")
            ,attr("ondblclick","dblclick")
            ,attr("onkeydown","keydown")
            ,attr("onkeypress","keypress")
            ,attr("onkeyup","keyup")
            ,attr("onmousedown","mousedown")
            ,attr("onmousemove","mousemove")
            ,attr("onmouseout","mouseout")
            ,attr("onmouseover","mouseover")
            ,attr("onmouseup","mouseup")
            ,attr("rules")
            ,attr("style")
            ,attr("summary")
            ,attr("title")
            ,attr("width")
        )).add("BootBreadCrumb",ar(
            attr("onclick","click","action")
            ,attr("ondblclick","dblclick")
            ,attr("onmousedown","mousedown")
            ,attr("onmousemove","mousemove")
            ,attr("onmouseout","mouseout")
            ,attr("onmouseover","mouseover")
            ,attr("onmouseup","mouseup")
            ,attr("style")
            ,attr("title")
        )).add("BootPanel",ar(
            attr("onclick","click","action")
            ,attr("ondblclick","dblclick")
            ,attr("onmousedown","mousedown")
            ,attr("onmousemove","mousemove")
            ,attr("onmouseout","mouseout")
            ,attr("onmouseover","mouseover")
            ,attr("onmouseup","mouseup")
            ,attr("style")
            ,attr("title")
        )).fix();
    public enum Key {
        COMMANDBUTTON("CommandButton"),
        FORMFORM("FormForm"),
        INPUTTEXT("InputText"),
        MESSAGESMESSAGES("MessagesMessages"),
        SELECTBOOLEANCHECKBOX("SelectBooleanCheckbox"),
        SELECTMANYMENU("SelectManyMenu"),
        SELECTMANYLIST("SelectManyList"),
        BOOTNAV("BootNav"),
        DATATABLE("DataTable"),
        BOOTBREADCRUMB("BootBreadCrumb"),
        BOOTPANEL("BootPanel"),
        SELECTINPUTGROUP("SelectInputGroup");

        private String key;
        Key(String key) {
            this.key = key;
        }
        public String value() {
            return this.key;
        }
    }


    public static Attribute[] getAttributes(Key key) {
        return ATTRIBUTE_LOOKUP.get(key.value());
    }
}
