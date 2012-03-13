package org.jensen.propedit.object;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PropertiesBundle {
	private List<PropertiesFile> files;
	private String name;
	private File directory;

	
	
	public PropertiesBundle(File directory, final String bundleName) throws IOException {
		super();
		
		this.directory = directory;
		this.name = bundleName;
		
		if ( (directory == null ) || (!directory.exists()) || (!directory.isDirectory())){
			throw new IOException(directory +" is no valid directory");
		}
		if (bundleName == null){
			throw new NullPointerException("bundle name must not be null");
		}
		FilenameFilter filter=new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return name.toLowerCase().startsWith(bundleName.toLowerCase());
			}
		};
		files = new ArrayList<PropertiesFile>();
		for (File aFile: directory.listFiles(filter)){
			files.add(new PropertiesFile(this, aFile));
		}
	}
	public List<PropertiesFile> getFiles() {
		return files;
	}
	public void setFiles(List<PropertiesFile> files) {
		this.files = files;
	}
	public String getName() {
		return name;
	}
	public File getDirectory() {
		return directory;
	}
	
	/**
	 * Adds a file.
	 *
	 * @param locale the locale
	 * @param useCountry the use country
	 */
	public void addFile(Locale locale, boolean useCountry) {
		String filename = name;
		if (locale != null) {
			filename += "_" + locale.getLanguage();
			if (useCountry) {
				filename += "_" + locale.getCountry();
			}
		}
		filename += ".properties";
		files.add(new PropertiesFile(this, new File(filename)));
	}	
}
