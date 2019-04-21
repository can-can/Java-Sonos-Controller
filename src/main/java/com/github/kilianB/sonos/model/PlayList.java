package com.github.kilianB.sonos.model;

public class PlayList {
	private String title;
	private String uri;

	public PlayList(String title, String uri) {
		this.title = title;
		this.uri = uri;
	}

	public String getTitle() {
		return title;
	}

	public String getUri() {
		return uri;
	}

	@Override public String toString() {
		return "PlayList{" + "title='" + title + '\'' + ", uri='" + uri + '\'' + '}';
	}
}
