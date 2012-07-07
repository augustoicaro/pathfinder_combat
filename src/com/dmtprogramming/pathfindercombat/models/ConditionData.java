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

public class ConditionData {
	
	private static final String TAG = "PFCombat:ConditionData";
	
	private String name;
	private String short_description;
	private String long_description;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortDescription() {
		return short_description;
	}
	public void setShortDescription(String short_description) {
		this.short_description = short_description;
	}
	public String getLongDescription() {
		return long_description;
	}
	public void setLongDescription(String long_description) {
		this.long_description = long_description;
	}
	
	public static List<ConditionData> loadConditions(InputStream xml) {
		List<ConditionData> conditions = new ArrayList<ConditionData>();
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(xml, null);
			NodeList condition_nodes = doc.getElementsByTagName("condition");
			for (int i = 0; i < condition_nodes.getLength(); i++) {
				Element elm = (Element)condition_nodes.item(i);
				ConditionData condition = new ConditionData();
				condition.setName(elm.getAttribute("name"));
				Log.d(TAG, "Adding condition with name = " + condition.getName());
				condition.setShortDescription(getDescriptionContent(elm, "short_description"));
				condition.setLongDescription(getDescriptionContent(elm, "long_description"));
				conditions.add(condition);

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
	
	private static String getDescriptionContent(Element elm, String tag) {
		NodeList list = elm.getElementsByTagName(tag);
		if (list.getLength() == 1) {
			Element description = (Element) list.item(0);
			return description.getTextContent().replace("\n", " ").replace("\t", " ");
		}
		return "";
	}
}
