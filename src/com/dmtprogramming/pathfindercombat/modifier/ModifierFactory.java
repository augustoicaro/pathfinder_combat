package com.dmtprogramming.pathfindercombat.modifier;

public class ModifierFactory {

	public static ModifierBase create(String name) {
		try {
			Class<?> clazz = Class.forName("com.dmtprogramming.pathfindercombat.modifier." + name);
			if (clazz.getSuperclass() == ModifierBase.class) {
				return (ModifierBase) clazz.newInstance();
			}
			throw new IllegalArgumentException("Provided class doesn't extend ModifierBase");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
