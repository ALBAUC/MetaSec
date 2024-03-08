package domain;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("metadata")
public class Metadata {

	private String key;
	private String value;
	private String filePath;

	public Metadata(String key, String value, String filePath) {
		super();
		this.key = key;
		this.value = value;
		this.filePath = filePath;
	}

	public Metadata() {
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String toString() {
		return "Metadata [key=" + key + ", value=" + value + ", filePath=" + filePath + "]";
	}

}
