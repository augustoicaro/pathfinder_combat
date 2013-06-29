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
import com.dmtprogramming.pathfindercombat.modifier.ModifierBase;
import com.dmtprogramming.pathfindercombat.modifier.ModifierFactory;

public class Toggles {
	private static final String TAG = "PFCombat";
	
	public static List<ModifierBase> loadToggles(InputStream xml) {
		List<ModifierBase> modifiers = new ArrayList<ModifierBase>();
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(xml, null);
			NodeList condition_nodes = doc.getElementsByTagName("toggle");
			for (int i = 0; i < condition_nodes.getLength(); i++) {
				Element elm = (Element)condition_nodes.item(i);
				Log.d(TAG, "Toggles: Adding toggle with name = " + elm.getAttribute("name"));
				modifiers.add(ModifierFactory.create(elm.getAttribute("name")));
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

		return modifiers;
	}
}
