package com.quakearts.appbase.test.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("testConversation")
@ConversationScoped
public class TestConversation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7114315575013563091L;
	@Inject
	Conversation conversation;
	
	@PostConstruct
	public void init() {
		if(conversation.isTransient()) {
			conversation.begin();
		}	
	}
	
	public String getHashCode() {
		return Integer.toHexString(hashCode());
	}
}
