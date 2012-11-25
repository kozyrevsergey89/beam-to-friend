package com.nfcfriend.jsonhandler.entity;

public class Post implements FacebookIdentifiable, FacebookStory{

	private String  id;
	private String message;
	private String story;
    private String type;

    @Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

    @Override
	public String getStory() {
        if(this.message != null && !"".equals(this.message.trim())) return this.message;
        if(this.story != null && !"".equals(this.story.trim())) return this.story;
		return "";
	}

	public void setStory(String story) {
		this.story = story;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", story=" + story + ", type=" + type + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Post other = (Post) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
