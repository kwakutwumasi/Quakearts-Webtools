package com.quakearts.appbase.spi.beans;

public class WebAppListener {
	public static enum Priority {
		ANY,
		FIRST,
		LAST
	}
	
	Class<?> listenerClass;
	private Priority priority;

	public WebAppListener(Class<?> listenerClass, Priority priority) {
		this.listenerClass = listenerClass;
		this.priority = priority;
	}
	
	public Class<?> getListenerClass() {
		return listenerClass;
	}
	
	public Priority getPriority() {
		return priority;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listenerClass == null) ? 0 : listenerClass.hashCode());
		result = prime * result + ((priority == null) ? 0 : priority.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WebAppListener other = (WebAppListener) obj;
		if (listenerClass == null) {
			if (other.listenerClass != null)
				return false;
		} else if (listenerClass != other.listenerClass)
			return false;
		if (priority != other.priority)
			return false;
		return true;
	}
	
	
}
