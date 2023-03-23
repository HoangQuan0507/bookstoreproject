package entities;

import java.util.Date;

public class Author {
	private int id;
	private String name;

	private String story;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
	
}
