/*
    Gravel - Component library for JSF
    Copyright (C) 2007  David M. Lloyd

    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either
    version 2.1 of the License, or (at your option) any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
/**
 * A tag library consisting of tags that control navigation flow.  To use
 * this tag library, you must configure your navigation handler as follows in
 * your <code>faces-config.xml</code>:
<pre>
&lt;application&gt;<br/>
...<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&lt;navigation-handler&gt;org.jboss.gravel.navigation.GravelNavigationHandler&lt;/navigation-handler&gt;<br/>
...<br/>
&lt;/application&gt;
</pre>
 */
@org.jboss.gravel.common.annotation.TldTagLib(
    fileName = "gravel-nav.tld",
    shortName = "n",
    uri = "http://gravel.jboss.org/jsf/1.0/navigation"
)
package org.jboss.gravel.navigation;
