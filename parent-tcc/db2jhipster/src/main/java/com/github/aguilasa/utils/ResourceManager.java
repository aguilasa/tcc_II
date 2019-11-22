package com.github.aguilasa.utils;

public class ResourceManager {

	private static final String RESOURCES = "src/main/resources/";

	private static ResourceManager instance = new ResourceManager();

	private ResourceManager() {

	}

	public String getRunningJarName() {
		String className = getClass().getName().replace('.', '/');
		String classJar = getClass().getResource("/" + className + ".class").toString();
		if (classJar.startsWith("jar:")) {
			String vals[] = classJar.split("/");
			for (String val : vals) {
				if (val.contains("!")) {
					return val.substring(0, val.length() - 1);
				}
			}
		}
		return null;
	}

	public static final boolean isRunningFromJar() {
		return instance.getRunningJarName() != null;
	}

	public static final String getPath(String path) {
		if (isRunningFromJar()) {
			return path;
		}
		return RESOURCES.concat(path);
	}

}
