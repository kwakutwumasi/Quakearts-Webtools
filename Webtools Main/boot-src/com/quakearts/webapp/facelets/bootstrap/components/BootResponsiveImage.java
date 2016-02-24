package com.quakearts.webapp.facelets.bootstrap.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.component.UIOutput;

import com.quakearts.webapp.facelets.util.ObjectExtractor;

public class BootResponsiveImage extends UIOutput {
	public static final String COMPONENT_FAMILY = "com.quakearts.bootstrap.bootresponsiveimage";
	public static final String RENDERER_TYPE = "com.quakearts.bootstrap.bootresponsiveimage.renderer";
	private List<ImageEntry> imageEntries = new ArrayList<>();

	public static final class ImageEntry implements Comparable<ImageEntry> {
		private int size;
		private String imagePath;

		public ImageEntry(int size, String imagePath) {
			this.size = size;
			this.imagePath = imagePath;
		}

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}

		public String getImagePath() {
			return imagePath;
		}

		public void setImagePath(String imagePath) {
			this.imagePath = imagePath;
		}

		@Override
		public int compareTo(ImageEntry o) {
			if(o==null)
				return 1;
			
			if(o.equals(this))
				return 0;
			
			return this.size - o.size;
		}
	}

	public List<ImageEntry> getImageEntries() {
		if(!imageEntries.isEmpty())
			Collections.sort(imageEntries);
		
		return Collections.unmodifiableList(imageEntries);
	}
	
	@Override
	public String getRendererType() {
		return RENDERER_TYPE;
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	public String get(String attribute) {
		String attributeValue = ObjectExtractor.extractString(getValueExpression(attribute),
				getFacesContext().getELContext());
		if (attributeValue == null)
			attributeValue = (String) getAttributes().get(attribute);

		return attributeValue;
	}

	public void addImageEntry(ImageEntry entry) {
		imageEntries.add(entry);
	}
}
