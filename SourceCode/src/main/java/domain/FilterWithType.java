package domain;

import java.util.Objects;

import javax.swing.RowFilter;

public class FilterWithType {

	private RowFilter<Object, Object> filter;
	private String text;
	private FILTER_TYPE type;

	public FilterWithType(RowFilter<Object, Object> filter, String text, FILTER_TYPE type) {
		super();
		this.filter = filter;
		this.text = text;
		this.type = type;
	}

	public RowFilter<Object, Object> getFilter() {
		return filter;
	}

	public void setFilter(RowFilter<Object, Object> filter) {
		this.filter = filter;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public FILTER_TYPE getType() {
		return type;
	}

	public void setType(FILTER_TYPE type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		FilterWithType other = (FilterWithType) obj;
		return Objects.equals(text, other.text) && type == other.type;
	}

}
