package com.quakearts.webapp.facelets.bootstrap.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import com.quakearts.webapp.facelets.base.BaseBean;
import com.quakearts.webapp.facelets.bootstrap.common.BootHeaderRenderer;
import com.quakearts.webapp.facelets.bootstrap.common.BootHeaderRenderer.Theme;

@ManagedBean(name = "greeting")
@ViewScoped
public class GreetingBean extends BaseBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String value; 
	private String  name;
	private String language="en-US"; 
	private String greetingType="standard"; 
	private String  selectedLanguageTag; 
	private Theme theme;
	private int numberOfPresents; 
	private String[][] languages= {{"English","en-US"},{"English (UK)","en-UK"},{"Akan","ak"}}; //BootSelectInputGroup.class
	private String[] greetingTypes = {"standard","formal","colloqial","pidgin"}; //BootSelectOneInput.class
	private Theme[] availableThemes = Theme.values(); //BootSelectOneListbox.class
	private String[][] availableLanguageTags = {{"French","fr_FR"},{"Yoruba","yo_NG"},{"Spanish","es"}}; //BootSelectOneMenu.class
	private String[][] actions= {{"addGreetingBundle","Add Greeting Bundle"},{"changeTheme", "Change Theme"},
			{"decrement","Decrement presents"},{"increment", "Increment presents"},{"reset","Reset page"},
			{"searchActionsWithFilter","Search Actions With Filter"},{"showAllActions", "Show All Actions"},
			{"updateGreeting","Update Greeting"},{"updateLanguage","Update Language"}};
	private String[] filterSelectedActions; //BootSelectManyMenu.class
	private String[] images = {"close-squirrel-1381764.jpg","daisy-s-1375598.jpg",
			"hinds-1638023.jpg","on-the-road-6-1384796.jpg","small-alpine-village-1639266.jpg",
			"small-alpine-village-italian-dolomites-1639269.jpg"};
	private String[] filterImages = images; //BootSelectManyListbox.class
	private String state; //BootBreadCrumb.class
	private boolean inAdvancedMode;//BootCheckbox.class
	private Date birthDate; //BootDateButton.class
	private byte[] messageBundleBytes; //BootFileInput.class
	private String messageBundleFileName; //BootFileInput.class
	private List<ActionLog> actionLogs; //BootPagination.class BootTable.class
	private static final List<ActionLog> ACTION_LOGS = Arrays.asList(
			new ActionLog().withAction("Action 1").withActionDate(new Date(System.currentTimeMillis() - 777_600_000l)).withActionHost("Test Host"),
			new ActionLog().withAction("Action 2").withActionDate(new Date(System.currentTimeMillis() - 691_200_000l)).withActionHost("Test Host"),
			new ActionLog().withAction("Action 3").withActionDate(new Date(System.currentTimeMillis() - 604_800_000l)).withActionHost("Test Host"),
			new ActionLog().withAction("Action 4").withActionDate(new Date(System.currentTimeMillis() - 518_400_000l)).withActionHost("Test Host"),
			new ActionLog().withAction("Action 5").withActionDate(new Date(System.currentTimeMillis() - 432_000_000l)).withActionHost("Test Host"),
			new ActionLog().withAction("Action 6").withActionDate(new Date(System.currentTimeMillis() - 345_600_000l)).withActionHost("Test Host"),
			new ActionLog().withAction("Action 7").withActionDate(new Date(System.currentTimeMillis() - 259_200_000l)).withActionHost("Test Host"),
			new ActionLog().withAction("Action 8").withActionDate(new Date(System.currentTimeMillis() - 172_800_000l)).withActionHost("Test Host"),
			new ActionLog().withAction("Action 9").withActionDate(new Date(System.currentTimeMillis() - 86_400_000l )).withActionHost("Test Host"),
			new ActionLog().withAction("Action 10").withActionDate(new Date(System.currentTimeMillis())).withActionHost("Test Host"));
	
	private List<String> names;	
	
	private static final List<String> NAMES = Arrays.asList("John Ababio", "William Adekorafo", "James Baako",
			"Charles Adjo", "George Baba", "Frank Afi", "Joseph Boahinmaa", "Thomas Ampah", "Henry Caimile",
			"Robert Ashon", "Edward Deladem", "Harry Bediako", "Walter Doe", "Arthur Bobo", "Fred Dofi", "Albert Botwe",
			"Samuel Duku", "David Bubune", "Louis Coblah", "Joe Ekuwa", "Charlie Abam", "Clarence Abina",
			"Richard Coffie", "Andrew Enyonyam", "Daniel Commie", "Ernest Fifi", "Will Danso", "Jesse Gifty",
			"Oscar Dodzi", "Lewis Haniah", "Peter Ekow", "Benjamin I’timad", "Frederick Enam", "Willie Ikhlas",
			"Alfred Fenuku", "Sam Jojo", "Roy Fodjour", "Herbert Kakra", "Jacob Gyasi", "Tom Kisi", "Elmer Kaatachi",
			"Carl Kunto", "Lee Aban", "Howard Adzo", "Martin Kuukuwa", "Michael Kwadwo", "Bert Kplorm", "Herman Likem",
			"Jim Kwamena", "Francis Lumusi", "Harvey Ababio", "Earl Adekorafo", "Eugene Baako", "Ralph Adjo", "Ed Baba",
			"Claude Afi", "Edwin Boahinmaa", "Ben Ampah", "Charley Caimile", "Paul Ashon", "Edgar Deladem",
			"Isaac Bediako", "Otto Doe", "Luther Bobo", "Lawrence Dofi", "Ira Botwe", "Patrick Duku", "Guy Bubune",
			"Oliver Coblah", "Theodore Ekuwa", "Hugh Abam", "Clyde Abina", "Alexander Coffie", "August Enyonyam");

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getGreetingType() {
		return greetingType;
	}

	public void setGreetingType(String greetingType) {
		this.greetingType = greetingType;
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	public String getSelectedLanguageTag() {
		return selectedLanguageTag;
	}

	public void setSelectedLanguageTag(String availableCulture) {
		this.selectedLanguageTag = availableCulture;
	}

	public int getNumberOfPresents() {
		return numberOfPresents;
	}

	public void setNumberOfPresents(int waitInterval) {
		this.numberOfPresents = waitInterval;
	}

	public String[] getFilterSelectedActions() {
		return filterSelectedActions;
	}

	public void setFilterSelectedActions(String[] filterSelectedActions) {
		this.filterSelectedActions = filterSelectedActions;
	}
	
	public String[] getFilterImages() {
		return filterImages;
	}

	public void setFilterImages(String[] filterImages) {
		this.filterImages = filterImages;
	}

	public String[] getImages() {
		return images;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isInAdvancedMode() {
		return inAdvancedMode;
	}

	public void setInAdvancedMode(boolean inAdvancedMode) {
		this.inAdvancedMode = inAdvancedMode;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public byte[] getMessageBundleBytes() {
		return messageBundleBytes;
	}

	public void setMessageBundleBytes(byte[] messageBundleBytes) {
		this.messageBundleBytes = messageBundleBytes;
	}

	public String getMessageBundleFileName() {
		return messageBundleFileName;
	}

	public void setMessageBundleFileName(String messageBundleFileName) {
		this.messageBundleFileName = messageBundleFileName;
	}

	public String[][] getLanguages() {
		return languages;
	}

	public String[] getGreetingTypes() {
		return greetingTypes;
	}

	public Theme[] getAvailableThemes() {
		return availableThemes;
	}

	public String[][] getAvailableLanguageTags() {
		return availableLanguageTags;
	}

	public String[][] getActions() {
		return actions;
	}

	public List<ActionLog> getActionLogs() {
		return actionLogs;
	}
		
	MessageFormat messageFormat = new MessageFormat(ResourceBundle.getBundle("greetings").getString(greetingType));
	
	public void changeTheme(ActionEvent event) {
		BootHeaderRenderer.setTheme(FacesContext.getCurrentInstance(), theme);
	}
	
	//BootButtonGroup.class
	public void increment(AjaxBehaviorEvent event) {
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		++numberOfPresents;
	}

	//BootButtonGroup.class
	public void decrement(AjaxBehaviorEvent event) {
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		--numberOfPresents;
	}
	
	//BootAjaxLoaderComponent.class BootCommandButton.class BootGlyph.class BootForm.class
	public void updateGreeting(AjaxBehaviorEvent event) {
		value = messageFormat.format(new Object[] {name, numberOfPresents, numberOfPresents==1?"present":"presents", birthDate});
	}
	
	//BootAjaxLoaderComponent.class BootCommandButton.class BootFontawesome.class BootForm.class
	public void updateLanguage(AjaxBehaviorEvent event) {
		messageFormat = new MessageFormat(
				ResourceBundle.getBundle("greetings", Locale.forLanguageTag(language)).getString(greetingType));
	}
	
	//BootAjaxLoaderComponent.class BootCommandButton.class BootFlaticon.class BootForm.class BootInputText.class
	public void addGreetingBundle(ActionEvent event) {
		if(messageBundleBytes!=null) {
			File file = new File("etc", "greeting_"+selectedLanguageTag+".properties");
			try(FileOutputStream fos = new FileOutputStream(file)){
				fos.write(messageBundleBytes);
				fos.flush();
			} catch (IOException e) {
				addError("Unable to save", "There was an error saving the new bundle", FacesContext.getCurrentInstance());
				return;
			}
			addMessage("Uploaded", "The bundle has been updated", FacesContext.getCurrentInstance());
		} else {
			addWarning("Nothing worked", "The file was not uploaded", FacesContext.getCurrentInstance());
		}
	}
	
	//BootButtonToolbar.class
	public void searchActionsWithFilter(AjaxBehaviorEvent event) {
		if(filterSelectedActions == null) {
			addError("No Filter Selected", "Select actions to filter out", FacesContext.getCurrentInstance());
			return;
		}
		
		actionLogs = ACTION_LOGS.stream().filter(actionLog->{
			for(String filterAction:filterSelectedActions) {
				if(actionLog.action.equals(filterAction))
					return true;
			}
			return false;
		}).collect(Collectors.toList());
	}
	
	//BootButtonToolbar.class
	public void showAllActions(AjaxBehaviorEvent event) {
		actionLogs = ACTION_LOGS;
	}
	
	private String suggestion;
	
	public String getSuggestion() {
		return suggestion;
	}
	
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	
	public void filter(AjaxBehaviorEvent event) {
		if(suggestion != null) {
			names = NAMES.stream().filter(name->name.contains(suggestion)).collect(Collectors.toList());
		}
	}
	
	public List<String> getNames() {
		return names;
	}
}
