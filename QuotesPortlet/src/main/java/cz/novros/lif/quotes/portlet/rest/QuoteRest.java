package cz.novros.lif.quotes.portlet.rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class QuoteRest {
	private Integer ID;
	private String title;
	private String content;
	private String link;
	
	public QuoteRest() {
		super();
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	
}
