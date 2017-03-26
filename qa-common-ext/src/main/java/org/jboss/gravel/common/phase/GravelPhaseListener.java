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
package org.jboss.gravel.common.phase;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.io.Reader;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.nio.channels.Channel;
import java.nio.channels.Selector;

import javax.faces.context.FacesContext;
import javax.faces.context.ExternalContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.el.ELContext;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.gravel.action.handler.KeepAliveHandler;

/**
 *
 */
public final class GravelPhaseListener implements PhaseListener {
    private static final long serialVersionUID = 1L;

    private static final String REQUEST_CLEANERS_KEY = "gravel.private.RequestCleaners";

    public void beforePhase(final PhaseEvent phaseEvent) {
        if (log.isLoggable(Level.FINE)) {
            log.log(Level.FINE, "Before phase " + phaseEvent.getPhaseId());
            if (phaseEvent.getPhaseId() == PhaseId.RESTORE_VIEW) {
                StringBuilder builder = new StringBuilder(400);
                builder.append("Request parameter map:");
                final FacesContext facesContext = phaseEvent.getFacesContext();
                final ExternalContext externalContext = facesContext.getExternalContext();
                final Map<String, String[]> parameterMap = externalContext.getRequestParameterValuesMap();
                for (String key : new TreeSet<String>(parameterMap.keySet())) {
                    final String[] values = parameterMap.get(key);
                    builder.append("\n    \"").append(key).append("\" => ");
                    boolean first = true;
                    for (String value : values) {
                        if (first) {
                            first = false;
                        } else {
                            builder.append(", ");
                        }
                        builder.append('"').append(value).append('"');
                    }
                }
                log.fine(builder.toString());
            }
        }
    }

    @SuppressWarnings ({"unchecked"})
    public void afterPhase(final PhaseEvent phaseEvent) {
        final PhaseId phaseId = phaseEvent.getPhaseId();
        final FacesContext facesContext = phaseEvent.getFacesContext();
        final boolean complete = facesContext.getResponseComplete();
        final boolean debug = log.isLoggable(Level.FINE);
        if (debug) {
            if (complete) {
                log.log(Level.FINE, "After phase " + phaseId + " (REPSONSE COMPLETE)");
            } else {
                log.log(Level.FINE, "After phase " + phaseId);
            }
        }
        if (phaseId == PhaseId.RENDER_RESPONSE || complete) {
            final List<Runnable> cleaners = getRequestCleaners(facesContext, false);
            if (cleaners != null) {
                if (debug) {
                    log.log(Level.FINE, "Executing " + cleaners.size() + " cleaners");
                }
                for (Runnable runnable : cleaners) {
                    try {
                        runnable.run();
                    } catch (RuntimeException ex) {
                        if (debug) {
                            log.log(Level.FINE, "Exception thrown on cleanup path", ex);
                        }
                    }
                }
            }
        } else if (phaseId == PhaseId.RESTORE_VIEW) {
            final Map<String,KeepAliveHandler.KeepAliveEntry> keepAliveList = (Map<String,KeepAliveHandler.KeepAliveEntry>)
                facesContext.getViewRoot().getAttributes().get("org.jboss.gravel.KeepAliveList");
            if (keepAliveList != null) {
                final ELContext elContext = facesContext.getELContext();
                for (KeepAliveHandler.KeepAliveEntry entry : keepAliveList.values()) {
                    if (debug) {
                        log.fine("Restoring keepAlive expression '" + entry.getValueExpression().getExpressionString() + "'");
                    }
                    entry.getValueExpression().setValue(elContext, entry.getValue());
                }
                keepAliveList.clear();
            }
        }
    }

    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

    private static final Logger log = Logger.getLogger("org.jboss.gravel.common.phase.GravelPhaseListener");

    @SuppressWarnings ({"unchecked"})
    public static List<Runnable> getRequestCleaners(FacesContext context) {
        return getRequestCleaners(context, true);
    }

    @SuppressWarnings ({"unchecked"})
    public static List<Runnable> getRequestCleaners(FacesContext context, boolean create) {
        final Map<String, Object> requestMap = context.getExternalContext().getRequestMap();
        final List<Runnable> requestCleaners;
        if (requestMap.containsKey(REQUEST_CLEANERS_KEY)) {
            requestCleaners = (List<Runnable>) requestMap.get(REQUEST_CLEANERS_KEY);
        } else {
            if (! create) return null;
            requestCleaners = new ArrayList<Runnable>();
            requestMap.put(REQUEST_CLEANERS_KEY, requestCleaners);
        }
        return requestCleaners;
    }

    public static Runnable fileCleaner(final File file) {
        return new Runnable() {
            private boolean ran = false;
            protected void finalize() throws Throwable {
                if (!ran) run();
                super.finalize();
            }
            public void run() {
                if (ran) return;
                file.delete();
                ran = true;
            }
        };
    }

    public static Runnable closeableCleaner(final Object resource) {
        return new Runnable() {
            private boolean ran = false;
            protected void finalize() throws Throwable {
                if (!ran) run();
                super.finalize();
            }
            public void run() {
                if (ran) return;
                try {
                    if (resource instanceof InputStream) {
                        ((InputStream)resource).close();
                    } else if (resource instanceof OutputStream) {
                        ((OutputStream)resource).close();
                    } else if (resource instanceof Reader) {
                        ((Reader)resource).close();
                    } else if (resource instanceof Writer) {
                        ((Writer)resource).close();
                    } else if (resource instanceof Channel) {
                        ((Channel)resource).close();
                    } else if (resource instanceof Selector) {
                        ((Selector)resource).close();
                    } else if (resource instanceof Closeable) {
                        ((Closeable)resource).close();
                    }
                } catch (IOException ex) {
                    // nothing
                }
                ran = true;
            }
        };
    }
}
