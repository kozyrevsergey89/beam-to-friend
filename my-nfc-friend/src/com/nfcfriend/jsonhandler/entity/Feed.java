package com.nfcfriend.jsonhandler.entity;

public class Feed implements FacebookIdentifiable, FacebookStory{

	private String id;
    private String story;
    private Application application;
    
	@Override
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

    @Override
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}
  	
	@Override
	public String toString() {
		return "Feed [id=" + id + ", story=" + story + ", application="
				+ application + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((application == null) ? 0 : application.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Feed other = (Feed) obj;
		if (application == null) {
			if (other.application != null)
				return false;
		} else if (!application.equals(other.application))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
