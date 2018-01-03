package intern.sungjunlee.data;

import java.util.ArrayList;
import java.util.List;

public class Data <T> {
	//protected T[] data;
	protected List<T> fields;
	protected int columnLength;
	
	public Data(int columnLength) {
		this.columnLength = columnLength;
		//data = (T[]) new Object[columnLength];
		fields = new ArrayList<T>(columnLength);
	}
	
	public void set(String rawLine) {
		
	}
	
	public T get(int idx) {
		return fields.get(idx);
	}
	
	public int columnLength() {
		return columnLength;
	}
}
