package com.quakearts.webapp.facelets.tag;

import javax.faces.FacesException;
import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.render.RenderKit;
import javax.faces.render.RenderKitFactory;

import com.quakearts.webapp.facelets.tag.handler.AddPropertyTagHandler;
import com.quakearts.webapp.facelets.tag.handler.AddToListHandler;
import com.quakearts.webapp.facelets.tag.handler.AutoCompleteSelectTagHandler;
import com.quakearts.webapp.facelets.tag.handler.AutoCompleteTagHandler;
import com.quakearts.webapp.facelets.tag.handler.CopyFileTagHandler;
import com.quakearts.webapp.facelets.tag.handler.CreateFileTagHandler;
import com.quakearts.webapp.facelets.tag.handler.CreateSelectItemsTagHandler;
import com.quakearts.webapp.facelets.tag.handler.DeleteFileTagHandler;
import com.quakearts.webapp.facelets.tag.handler.GetBytesTagHandler;
import com.quakearts.webapp.facelets.tag.handler.ListPropertiesTagHandler;
import com.quakearts.webapp.facelets.tag.handler.LoadPropertiesHandler;
import com.quakearts.webapp.facelets.tag.handler.MergeObjectTagHandler;
import com.quakearts.webapp.facelets.tag.handler.ProfileTagHandler;
import com.quakearts.webapp.facelets.tag.handler.RemoveFromListHandler;
import com.quakearts.webapp.facelets.tag.handler.RemoveFromMapHandler;
import com.quakearts.webapp.facelets.tag.handler.RemovePropertyTagHandler;
import com.quakearts.webapp.facelets.tag.handler.SaveFileHandler;
import com.quakearts.webapp.facelets.tag.handler.SaveOrUpdateObjectTagHandler;
import com.quakearts.webapp.facelets.tag.handler.SavePropertiesHandler;
import com.quakearts.webapp.facelets.tag.handler.SessionTagHandler;
import com.quakearts.webapp.facelets.tag.input.DateComponent;
import com.quakearts.webapp.facelets.tag.input.DateRenderer;
import com.quakearts.webapp.facelets.tag.input.DateTagHandler;
import com.quakearts.webapp.facelets.tag.input.converter.TimeStampTagHandler;
import com.quakearts.webapp.facelets.tag.input.validator.FileNameValidatorTagHandler;
import com.quakearts.webapp.hibernate.HibernateHelper;
import com.sun.faces.facelets.tag.AbstractTagLibrary;

public class CoreLibrary extends AbstractTagLibrary {
	public CoreLibrary() {
		super("http://quakearts.com/common");
		addTagHandler("convertTimeStamp", TimeStampTagHandler.class);
		addTagHandler("loadProps", LoadPropertiesHandler.class);
		addTagHandler("saveProps", SavePropertiesHandler.class);
		addTagHandler("addProp", AddPropertyTagHandler.class);
		addTagHandler("removeProp", RemovePropertyTagHandler.class);
		addTagHandler("list", ListPropertiesTagHandler.class);
		addTagHandler("createFile", CreateFileTagHandler.class);
		addTagHandler("deleteFile", DeleteFileTagHandler.class);
		addTagHandler("fileNameValidator", FileNameValidatorTagHandler.class);
		addTagHandler("createSelectItems", CreateSelectItemsTagHandler.class);
		addTagHandler("session", SessionTagHandler.class);
		addTagHandler("merge", MergeObjectTagHandler.class);
		addTagHandler("saveUpdate", SaveOrUpdateObjectTagHandler.class);
		addTagHandler("copyFile", CopyFileTagHandler.class);
		addTagHandler("addTolist", AddToListHandler.class);
		addTagHandler("removeFromlist", RemoveFromListHandler.class);
		addTagHandler("removeFromMap", RemoveFromMapHandler.class);
        addTagHandler("autoCompleteSelect", AutoCompleteSelectTagHandler.class);
        addTagHandler("autoComplete", AutoCompleteTagHandler.class);
        addTagHandler("removeFromList", RemoveFromListHandler.class);
        addTagHandler("profile", ProfileTagHandler.class);
        addTagHandler("getBytes", GetBytesTagHandler.class);
        addTagHandler("saveFile", SaveFileHandler.class);
		
		FacesContext context = FacesContext.getCurrentInstance(); 
		RenderKitFactory factory = (RenderKitFactory) FactoryFinder.getFactory(FactoryFinder.RENDER_KIT_FACTORY);
		RenderKit kit =	factory.getRenderKit(context, RenderKitFactory.HTML_BASIC_RENDER_KIT);
		Application app = context.getApplication();
		kit.addRenderer(DateComponent.COMPONENT_FAMILY, DateRenderer.RENDERER_TYPE, new DateRenderer());
		app.addComponent(DateComponent.COMPONENT_TYPE, DateComponent.class.getName());
		addComponent("date", DateComponent.COMPONENT_TYPE, DateRenderer.RENDERER_TYPE,DateTagHandler.class);
		
		try {
			addFunction("refresh", HibernateHelper.class.getMethod("refresh", Object.class, String.class));
		} catch (Exception e) {
			throw new FacesException("Error loading HibernateHelper refresh method",e);
		} 
	}

}
