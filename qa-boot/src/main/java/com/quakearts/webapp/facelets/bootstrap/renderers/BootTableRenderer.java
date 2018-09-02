/*******************************************************************************
* Copyright (C) 2016 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.webapp.facelets.bootstrap.renderers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIColumn;
import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import com.quakearts.webapp.facelets.bootstrap.components.BootTable;
import com.quakearts.webapp.facelets.bootstrap.renderkit.Attribute;
import com.quakearts.webapp.facelets.bootstrap.renderkit.AttributeManager;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicRenderer;
import com.quakearts.webapp.facelets.util.UtilityMethods;

import static com.quakearts.webapp.facelets.bootstrap.renderkit.RenderKitUtils.*;

public class BootTableRenderer extends HtmlBasicRenderer {
    private static final Attribute[] ATTRIBUTES =
            AttributeManager.getAttributes(AttributeManager.Key.DATATABLE);

    @Override
    public void encodeBegin(FacesContext context, UIComponent component)
          throws IOException {

    	if (!shouldEncode(component)) {
            return;
        }

        BootTable data = (BootTable) component;
        data.setRowIndex(-1);

        ResponseWriter writer = context.getResponseWriter();

		writer.startElement("table", component);
        writer.writeAttribute("id", component.getClientId(context),
                "id");
		String styleClass = data.get("styleClass");
		writer.writeAttribute("class","table "+(styleClass !=null?" "+styleClass:""), "styleClass");

		renderHTML5DataAttributes(context, component);
		renderPassThruAttributes(context, writer, component,
				ATTRIBUTES);
		writer.writeText("\n", component, null);


		UIComponent caption = getFacet(component, "caption");
		if (caption != null) {
			String captionClass = data.get("captionClass");
			String captionStyle = data.get("captionStyle");
			writer.startElement("caption", component);
			if (captionClass != null) {
				writer.writeAttribute("class", captionClass, "captionClass");
			}
			if (captionStyle != null) {
				writer.writeAttribute("style", captionStyle, "captionStyle");
			}
			encodeRecursive(context, caption);
			writer.endElement("caption");
		}


		UIComponent colGroups = getFacet(component, "colgroups");
		if (colGroups != null) {
			encodeRecursive(context, colGroups);
		}

		BootMetaInfo info = getMetaInfo(context, component);
		UIComponent header = getFacet(component, "header");

		if (header != null || info.hasHeaderFacets) {
			String headerClass = data.get("headerClass");
			writer.startElement("thead", component);
			writer.writeText("\n", component, null);
			if (header != null) {
				writer.startElement("tr", header);
				writer.startElement("th", header);
				if (headerClass != null) {
					writer.writeAttribute("class", headerClass, "headerClass");
				}
				if (info.columns.size() > 1) {
					writer.writeAttribute("colspan",
							String.valueOf(info.columns.size()), null);
				}
				writer.writeAttribute("scope", "colgroup", null);
				encodeRecursive(context, header);
				writer.endElement("th");
				writer.endElement("tr");
				writer.write("\n");
			}
			
			if (info.hasHeaderFacets) {
				writer.startElement("tr", component);
				writer.writeText("\n", component, null);
				for (UIColumn column : info.columns) {
					String columnHeaderClass = info.getCurrentHeaderClass();
					writer.startElement("th", column);
					if (columnHeaderClass != null) {
						writer.writeAttribute("class", columnHeaderClass,
								"columnHeaderClass");
					} else if (headerClass != null) {
						writer.writeAttribute("class", headerClass, "headerClass");
					}
					writer.writeAttribute("scope", "col", null);
					UIComponent facet = getFacet(column, "header");
					if (facet != null) {
						encodeRecursive(context, facet);
					}
					writer.endElement("th");
					writer.writeText("\n", component, null);
				}
				writer.endElement("tr");
				writer.write("\n");
			}
			writer.endElement("thead");
			writer.writeText("\n", component, null);
		}
    }
	
    @Override
    public void encodeChildren(FacesContext context, UIComponent component)
          throws IOException {

    	if (!shouldEncodeChildren(component)) {
            return;
        }

        UIData data = (UIData) component;

        ResponseWriter writer = context.getResponseWriter();

        BootMetaInfo info = getMetaInfo(context, data);
        if(info.columns.isEmpty()) {
    		writer.startElement("tbody", component);
    		renderEmptyTableRow(writer, component);
    		writer.endElement("tbody");
        	return;
        }

        int processed = 0;
        int rowIndex = data.getFirst() - 1;
        int rows = data.getRows();
        List<Integer> bodyRows = getBodyRows(context.getExternalContext().getApplicationMap(), data);
        boolean hasBodyRows = (bodyRows != null && !bodyRows.isEmpty());
        boolean wroteTableBody = false;
        if (!hasBodyRows) {
    		writer.startElement("tbody", component);
    		writer.writeText("\n", component, null);
        }
        boolean renderedRow = false;
        while (true) {

           if ((rows > 0) && (++processed > rows)) {
                break;
            }

            data.setRowIndex(++rowIndex);
            if (!data.isRowAvailable()) {
                break; 
            }

            if (hasBodyRows && bodyRows.contains(data.getRowIndex())) {
                if (wroteTableBody) {
                    writer.endElement("tbody");
                }
                writer.startElement("tbody", data);
                wroteTableBody = true;
            }

    		writer.startElement("tr", component);
    		if (info.rowClasses.length > 0) {
    			writer.writeAttribute("class", info.getCurrentRowClass(),
    					"rowClasses");
    		}
    		writer.writeText("\n", component, null);

    		info.newRow();
    		for (UIColumn column : info.columns) {

    			boolean isRowHeader = Boolean.TRUE.equals(column.getAttributes()
    					.get("rowHeader"));
    			if (isRowHeader) {
    				writer.startElement("th", column);
    				writer.writeAttribute("scope", "row", null);
    			} else {
    				writer.startElement("td", column);
    			}

    			String columnClass = info.getCurrentColumnClass();
    			if (columnClass != null) {
    				writer.writeAttribute("class", columnClass, "columnClasses");
    			}

    			for (Iterator<UIComponent> gkids = getChildren(column); gkids
    					.hasNext();) {
    				encodeRecursive(context, gkids.next());
    			}

    			if (isRowHeader) {
    				writer.endElement("th");
    			} else {
    				writer.endElement("td");
    			}
    			writer.writeText("\n", component, null);
    		}
    		
			writer.endElement("tr");
			writer.write("\n");
            renderedRow = true;
        }

        if(!renderedRow) {
        	renderEmptyTableRow(writer, data);
        }
        
		writer.endElement("tbody");
		writer.writeText("\n", component, null);

        data.setRowIndex(-1);
    }
    
    @Override
    public void encodeEnd(FacesContext context, UIComponent component)
          throws IOException {

        if (!shouldEncode(component)) {
            return;
        }
        
        ResponseWriter writer = context.getResponseWriter();
        
		BootMetaInfo info = getMetaInfo(context, component);
		UIComponent footer = getFacet(component, "footer");

		if (footer != null || info.hasFooterFacets) {
			String footerClass = (String) component.getAttributes().get("footerClass");
			writer.startElement("tfoot", component);
			writer.writeText("\n", component, null);
			if (info.hasFooterFacets) {
				writer.startElement("tr", component);
				writer.writeText("\n", component, null);
				for (UIColumn column : info.columns) {
					String columnFooterClass = (String) column.getAttributes().get(
							"footerClass");
					writer.startElement("td", column);
					if (columnFooterClass != null) {
						writer.writeAttribute("class", columnFooterClass,
								"columnFooterClass");
					} else if (footerClass != null) {
						writer.writeAttribute("class", footerClass, "footerClass");
					}
					UIComponent facet = getFacet(column, "footer");
					if (facet != null) {
						encodeRecursive(context, facet);
					}
					writer.endElement("td");
					writer.writeText("\n", component, null);
				}
				writer.endElement("tr");
				writer.write("\n");
			}
			if (footer != null) {
				writer.startElement("tr", footer);
				writer.startElement("td", footer);
				if (footerClass != null) {
					writer.writeAttribute("class", footerClass, "footerClass");
				}
				if (info.columns.size() > 1) {
					writer.writeAttribute("colspan",
							String.valueOf(info.columns.size()), null);
				}
				encodeRecursive(context, footer);
				writer.endElement("td");
				writer.endElement("tr");
				writer.write("\n");
			}
			writer.endElement("tfoot");
			writer.writeText("\n", component, null);
		}

        clearMetaInfo(context, component);
        ((UIData) component).setRowIndex(-1);
        
		writer.endElement("table");
		writer.writeText("\n", component, null);
    }

    private List<Integer> getBodyRows(Map<String, Object> appMap, UIData data) {

        List<Integer> result = null;
        String bodyRows = (String) data.getAttributes().get("bodyrows");
        if (bodyRows != null) {
            String [] rows = UtilityMethods.split(appMap, bodyRows, ",");
            if (rows != null) {
                result = new ArrayList<Integer>(rows.length);
                for (String curRow : rows) {
                    result.add(Integer.valueOf(curRow));
                }
            }
        }

        return result;
     }
			
	private void renderEmptyTableRow(final ResponseWriter writer,
			final UIComponent component) throws IOException {

		writer.startElement("tr", component);
		writer.startElement("td", component);
		writer.endElement("td");
		writer.endElement("tr");

	}
		
	protected BootTableRenderer.BootMetaInfo getMetaInfo(FacesContext context,
			UIComponent table) {

		String key = createKey(table);
		Map<Object, Object> attributes = context.getAttributes();
		BootMetaInfo info = (BootMetaInfo) attributes
				.get(key);
		if (info == null) {
			info = new BootMetaInfo(table);
			attributes.put(key, info);
		}
		return info;

	}
  
    protected void clearMetaInfo(FacesContext context, UIComponent table) {

        context.getAttributes().remove(createKey(table));

    }
    
    protected String createKey(UIComponent table) {

        return BootMetaInfo.KEY + '_' + table.hashCode();

    }
    
	private static class BootMetaInfo {

		private static final UIColumn PLACE_HOLDER_COLUMN = new UIColumn();
		private static final String[] EMPTY_STRING_ARRAY = new String[0];
		public static final String KEY = BootMetaInfo.class.getName();
		public final String[] rowClasses;
		public final String[] columnClasses;
		public final String[] headerClasses;
		public final List<UIColumn> columns;
		public final boolean hasHeaderFacets;
		public final boolean hasFooterFacets;
		public final int columnCount;
		public int columnStyleCounter;
		public int headerStyleCounter;
		public int rowStyleCounter;

		public BootMetaInfo(UIComponent table) {
			rowClasses = getRowClasses(table);
			columnClasses = getColumnClasses(table);
			headerClasses = getHeaderClasses(table);
			columns = getColumns(table);
			columnCount = columns.size();
			hasHeaderFacets = hasFacet("header", columns);
			hasFooterFacets = hasFacet("footer", columns);
		}

		public void newRow() {
			columnStyleCounter = 0;
			headerStyleCounter = 0;
		}

		public String getCurrentColumnClass() {
			String style = null;
			if (columnStyleCounter < columnClasses.length
					&& columnStyleCounter <= columnCount) {
				style = columnClasses[columnStyleCounter++];
			}
			return ((style != null && style.length() > 0) ? style : null);
		}

		public String getCurrentHeaderClass() {
			String style = null;
			if (headerStyleCounter < headerClasses.length
					&& headerStyleCounter <= columnCount) {
				style = headerClasses[headerStyleCounter++];
			}
			return ((style != null && style.length() > 0) ? style : null);
		}

		public String getCurrentRowClass() {
			String style = rowClasses[rowStyleCounter++];
			if (rowStyleCounter >= rowClasses.length) {
				rowStyleCounter = 0;
			}
			return style;
		}

		private static String[] getColumnClasses(UIComponent table) {
			String values = ((BootTable) table).get("columnClasses");
			if (values == null) {
				return EMPTY_STRING_ARRAY;
			}
			Map<String, Object> appMap = FacesContext.getCurrentInstance()
					.getExternalContext().getApplicationMap();

			return UtilityMethods.split(appMap, values.trim(), ",");
		}

		private static String[] getHeaderClasses(UIComponent table) {

			String values = ((BootTable) table).get("headerClasses");
			if (values == null) {
				return EMPTY_STRING_ARRAY;
			}
			Map<String, Object> appMap = FacesContext.getCurrentInstance()
					.getExternalContext().getApplicationMap();

			return UtilityMethods.split(appMap, values.trim(), ",");
		}

		private static List<UIColumn> getColumns(UIComponent table) {

			if (table instanceof UIData) {
				int childCount = table.getChildCount();
				if (childCount > 0) {
					List<UIColumn> results = new ArrayList<UIColumn>(childCount);
					for (UIComponent kid : table.getChildren()) {
						if ((kid instanceof UIColumn) && kid.isRendered()) {
							results.add((UIColumn) kid);
						}
					}
					return results;
				} else {
					return Collections.emptyList();
				}
			} else {
				int count;
				Object value = table.getAttributes().get("columns");
				if ((value != null) && (value instanceof Integer)) {
					count = ((Integer) value);
				} else {
					count = 2;
				}
				if (count < 1) {
					count = 1;
				}
				List<UIColumn> result = new ArrayList<UIColumn>(count);
				for (int i = 0; i < count; i++) {
					result.add(PLACE_HOLDER_COLUMN);
				}
				return result;
			}

		}

		private static boolean hasFacet(String name, List<UIColumn> columns) {

			if (!columns.isEmpty()) {
				for (UIColumn column : columns) {
					if (column.getFacetCount() > 0) {
						if (column.getFacets().containsKey(name)) {
							return true;
						}
					}
				}
			}
			return false;

		}

		private static String[] getRowClasses(UIComponent table) {

			String values = ((BootTable) table).get("rowClasses");
			if (values == null) {
				return (EMPTY_STRING_ARRAY);
			}
			Map<String, Object> appMap = FacesContext.getCurrentInstance()
					.getExternalContext().getApplicationMap();

			return UtilityMethods.split(appMap, values.trim(), ",");
		}

	}
}
