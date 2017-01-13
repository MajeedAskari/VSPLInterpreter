package compiler;

import java.util.ArrayList;

public class SymbolTable {
	ArrayList<Variable> table = new ArrayList<>();

	public boolean contains(String name) {
		for (Variable row : table) {
			if (row.getName().equals(name))
				return true;
		}
		return false;
	}

	public void add(String name, String type, float value) throws Exception {
		if (contains(name))
			throw new Exception(name + "already exists");
		else {
			table.add(new Variable(name, type, value));
		}

	}

	public float getValue(String name) throws Exception {
		if (!contains(name))
			throw new Exception(name + "doesn't exsit");
		else {
			for (Variable row : table) {
				if (row.getName().equals(name))
					return row.getValue();
			}
		}
		return 0;
	}

	public void setValue(String name, float value) throws Exception {
		if (!contains(name))
			throw new Exception(name + " doesn't exsit");
		else {
			for (Variable row : table) {
				if (row.getName().equals(name)) {
					if (row.getType().equals("int"))
						row.setValue((int) value);
					else
						row.setValue(value);
				}
			}
		}

	}

	public String getType(String name) throws Exception {
		if (!contains(name))
			throw new Exception(name + " doesn't exsit");
		else {
			for (Variable row : table) {
				if (row.getName().equals(name))
					return row.getType();
			}
		}
		return "";
	}

}

class Variable {
	private String name, type;
	private float value;

	public Variable(String name, String type, float value) {
		super();
		this.name = name;
		this.type = type;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

}