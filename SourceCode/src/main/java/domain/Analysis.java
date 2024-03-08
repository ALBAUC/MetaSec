package domain;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;


@JsonTypeName("analysis")
public class Analysis {

	private String name;
	private String absolutePath;
	@JsonProperty("metadataList")
	private List<Metadata> metadataList;

	/*
	 * path can be an URL or a directory/file path.
	 */
	public Analysis(String path) {
		super();
		this.absolutePath = path;
		this.name = null;
		this.metadataList = Collections.synchronizedList(new LinkedList<Metadata>());
	}

	public Analysis() {
	}
	public List<Metadata> getMetadataList() {
		return metadataList;
	}

	public void setMetadataList(List<Metadata> metadataList) {
		this.metadataList = metadataList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbsolutePath() {
		return absolutePath;
	}

	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}

	@Override
	public String toString() {
		return "Analysis [name=" + name + ", absolutePath=" + absolutePath + ", metadataList=" + metadataList + "]";
	}

	
}
