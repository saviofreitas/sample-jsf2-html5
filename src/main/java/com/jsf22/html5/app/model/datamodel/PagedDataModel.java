package com.jsf22.html5.app.model.datamodel;

import java.util.List;

import javax.faces.model.DataModel;

public class PagedDataModel<T> extends DataModel<T> {

	private int rowIndex = -1;
	private int totalRows;
	private int pageSize;
	private List<T> list;

	public PagedDataModel() {
		super();
	}

	public PagedDataModel(List<T> list, int totalRows) {
		super();
		setWrappedData(list);
		this.totalRows = totalRows;
		this.pageSize = list.size();
	}

	@Override
	public int getRowCount() {
		return totalRows;
	}

	@Override
	public T getRowData() {
		if (list == null) {
			return null;
		} else if (!isRowAvailable()) {
			throw new IllegalArgumentException();
		} else {
			return list.get(getRowIndex());
		}
	}

	@Override
	public int getRowIndex() {
		return (rowIndex % pageSize);
	}

	@Override
	public Object getWrappedData() {
		return null;
	}

	@Override
	public boolean isRowAvailable() {
		if (list == null)
			return false;

		int rowIndex = getRowIndex();
		if (rowIndex >= 0 && rowIndex < list.size())
			return true;
		else
			return false;
	}

	@Override
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setWrappedData(Object list) {
		this.list = (List<T>) list;
	}

}
