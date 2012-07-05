package com.dmtprogramming.pathfindercombat.models;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.dmtprogramming.pathfindercombat.Log;

public class Condition {	
	
	private static final String TAG = "PFCombat:Condition";
	
	private long id;
	private long character_id;
	private String name;
	private long duration;
	private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCharacterId() {
		return character_id;
	}
	public void setCharacterId(long character_id) {
		this.character_id = character_id;
	}
	
	public static List<Condition> loadConditions(InputStream xml) {
		List<Condition> conditions = new ArrayList<Condition>();
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(xml, null);
			NodeList condition_nodes = doc.getElementsByTagName("condition");
			for (int i = 0; i < condition_nodes.getLength(); i++) {
				Element elm = (Element)condition_nodes.item(i);
				Condition condition = new Condition();
				condition.setName(elm.getAttribute("name"));
				condition.setDescription(elm.getAttribute("description"));
				conditions.add(condition);
				Log.d(TAG, "Adding condition with name = " + condition.getName());
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conditions;
	}
}
