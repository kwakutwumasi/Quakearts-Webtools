package org.jboss.gravel.data.action;

import java.util.Collection;
import java.util.Map;
import java.io.Serializable;

import javax.faces.event.ActionListener;
import javax.faces.event.ActionEvent;
import javax.faces.event.AbortProcessingException;
import javax.faces.context.FacesContext;
import javax.faces.FacesException;
import javax.el.ValueExpression;
import javax.el.ELContext;

/**
 *
 */
public final class PagerActionListener implements ActionListener, Serializable {
    private static final long serialVersionUID = 1L;

    private final ValueExpression valueExpression;
    private final ValueExpression pageExpression;
    private final ValueExpression pageSizeExpression;
    private final ValueExpression targetExpression;

    public PagerActionListener(final ValueExpression valueExpression, final ValueExpression pageExpression, final ValueExpression pageSizeExpression, final ValueExpression targetExpression) {
        this.valueExpression = valueExpression;
        this.pageExpression = pageExpression;
        this.pageSizeExpression = pageSizeExpression;
        this.targetExpression = targetExpression;
    }

    public void processAction(ActionEvent event) throws AbortProcessingException {
        final FacesContext facesContext = FacesContext.getCurrentInstance();
        final ELContext elContext = facesContext.getELContext();
        final Object value = valueExpression.getValue(elContext);
        final int size;
        if (value == null) {
            size = 0;
        } else if (value instanceof Collection) {
            final Collection collection = (Collection) value;
            size = collection.size();
        } else if (value instanceof Map) {
            final Map map = (Map) value;
            size = map.size();
        } else {
            throw new FacesException("Attempted to apply paging to an object that is not a Collection or a Map");
        }
        final Object pageValue = pageExpression == null ? null : pageExpression.getValue(elContext);
        final int pageParameter = pageValue == null ? 1 : pageValue instanceof Number ? ((Number)pageValue).intValue() : Integer.parseInt(pageValue.toString());
        final Object pageSizeValue = pageSizeExpression == null ? null : pageSizeExpression.getValue(elContext);
        final int pageSize = pageSizeValue == null ? 10 : pageSizeValue instanceof Number ? ((Number)pageSizeValue).intValue() : Integer.parseInt(pageSizeValue.toString());
        final int totalPages = (size + pageSize - 1) / pageSize;
        final int page = pageParameter < 1 ? 1 : pageParameter > totalPages ? totalPages : pageParameter;
        final int thisPageCount = page == totalPages ? ((size - 1) % pageSize) + 1 : pageSize;
        final int first = (page - 1) * pageSize;
        targetExpression.setValue(elContext, new Bean(page, totalPages, pageSize, thisPageCount, first, size));
    }

    public static final class Bean implements Serializable {
        private static final long serialVersionUID = 1L;

        private final int page;
        private final int totalPages;
        private final int pageSize;
        private final int thisPageSize;
        private final int first;
        private final int size;

        public Bean(final int page, final int totalPages, final int pageSize, final int thisPageSize, final int first, final int size) {
            this.page = page;
            this.totalPages = totalPages;
            this.pageSize = pageSize;
            this.thisPageSize = thisPageSize;
            this.first = first;
            this.size = size;
        }

        public int getPage() {
            return page;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public int getPageSize() {
            return pageSize;
        }

        public int getThisPageSize() {
            return thisPageSize;
        }

        public boolean isFirstPage() {
            return page == 1;
        }

        public boolean isLastPage() {
            return page == totalPages;
        }

        public int getFirst() {
            return first;
        }

        public int getLimit() {
            return first + thisPageSize;
        }

        public int getSize() {
            return size;
        }
    }
}
