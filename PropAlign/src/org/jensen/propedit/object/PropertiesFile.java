package org.jensen.propedit.object;

import java.io.File;
import java.util.List;
import java.util.Locale;

public class PropertiesFile {

	private PropertiesBundle bundle;
	private Locale locale;
	private boolean useCountry;
	private List<Property> properties;
	private boolean sortMaster;
	private File file;
	private boolean defaultFile;

	
	public PropertiesFile(PropertiesBundle bundle, File file) {
		super();
		this.bundle = bundle;
		this.file = file;
		extractInfo(file);
	}

	public File getFile() {
		return file;
	}
	
	public PropertiesBundle getBundle() {
		return bundle;
	}
	public void setBundle(PropertiesBundle bundle) {
		this.bundle = bundle;
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public boolean isUseCountry() {
		return useCountry;
	}
	public void setUseCountry(boolean useCountry) {
		this.useCountry = useCountry;
	}
	public List<Property> getProperties() {
		return properties;
	}
	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}
	public boolean isSortMaster() {
		return sortMaster;
	}
	public void setSortMaster(boolean sortMaster) {
		this.sortMaster = sortMaster;
	}
	public boolean isDefaultFile() {
		return defaultFile;
	}
	
	public void sortAsMaster(){
		// TODO: implement me
	}


	private void extractInfo(File file) {
		String fileName = file.getName();
		fileName = fileName.replaceFirst(bundle.getName(), "").replace(".properties", "");
		boolean valid=true;
		if (fileName.isEmpty()){
			this.defaultFile=true;
		}else{
			this.defaultFile=false;
			if (fileName.length() == 3){
				useCountry = false;
				if (!fileName.startsWith("_")){
					valid=false;
				}
				locale = new Locale(fileName.substring(1));
			}else if ( fileName.length() == 6 ){
				if ( ! ( (fileName.charAt(0) == '_') && (fileName.charAt(3) == '_'))){
					valid=false;
				}else{
					locale = new Locale(fileName.substring(1, 3), fileName.substring(4));
					useCountry = true;
				}
			} else {
				valid = false;
			}
		}
		
		if (!valid){
			throw new RuntimeException("Invalid locale identifier: " + fileName);
		}
	}

}
