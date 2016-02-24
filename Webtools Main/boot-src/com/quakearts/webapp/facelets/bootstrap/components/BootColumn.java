package com.quakearts.webapp.facelets.bootstrap.components;

import javax.el.ValueExpression;
import javax.faces.component.UIOutput;
import com.quakearts.webapp.facelets.util.ObjectExtractor;

public class BootColumn extends UIOutput {
	
	public static final String COMPONENT_FAMILY="com.quakearts.bootstrap.bootcolumn";
	public static final String RENDERER_TYPE="com.quakearts.bootstrap.bootcolumn.renderer";
	private int xs=-1, sm=-1, md=-1, lg=-1, offsetxs=-1, offsetsm=-1, offsetmd=-1, offsetlg=-1;
	
	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}
	
	@Override
	public String getRendererType() {
		return RENDERER_TYPE;
	}
	
	@Override
	public void setRendererType(String rendererType) {
	}
	
	public int xsSize(){
		if(xs<0)
			xs = getSize("xs");
		return xs;
	}

	public int smSize(){
		if(sm<0)
			sm = getSize("sm");
		return sm;
	}
	
	public int mdSize(){
		if(md<0)
			md = getSize("md");
		return md;
	}
	
	public int lgSize(){
		if(lg<0)
			lg = getSize("lg");
		return lg;
	}
	
	public int offsetxsSize(){
		if(offsetxs<0)
			offsetxs = getSize("offsetxs");
		return offsetxs;
	}

	public int offsetsmSize(){
		if(offsetsm<0)
			offsetsm = getSize("offsetsm");
		return offsetsm;
	}
	
	public int offsetmdSize(){
		if(offsetmd<0)
			offsetmd = getSize("offsetmd");
		return offsetmd;
	}
	
	public int offsetlgSize(){
		if(offsetlg<0)
			offsetlg = getSize("offsetlg");
		return offsetlg;
	}
	
	private int getSize(String attribute){
		ValueExpression sizeExpression;
		int size;
        if((sizeExpression = getValueExpression(attribute))!=null)
        	size = ObjectExtractor.extractInteger(sizeExpression, getFacesContext().getELContext());
        else
        	try {
				size = Integer.parseInt(((String) getAttributes().get(attribute)));
			} catch (Exception e) {
				size = 0;
			}
        return size;
	}
	
	public String get(String attribute) {
		String attributeValue = ObjectExtractor
				.extractString(getValueExpression(attribute), getFacesContext()
						.getELContext());
		if (attributeValue == null)
			attributeValue = (String) getAttributes().get(attribute);

		return attributeValue;
	}
}
