package compiler;

import java.util.ArrayList;

public class LabelTable {

	ArrayList<Label> labels = new ArrayList<>();

	public boolean contains(String name) {
		for (Label label : labels) {
			if (label.name.equals(name))
				return true;
		}
		return false;
	}

	public void add(String name, int lineNumber) throws Exception {
		if (contains(name))
			throw new Exception("label" + name + "already exists!");
		else {
			labels.add(new Label(name, lineNumber));
		}
	}

	public int getLine(String name) throws Exception {
		for (Label label : labels) {
			if (label.name.equals(name))
				return label.lineNumber;
		}
		throw new Exception("label " + name + " not found!");
	}

}

class Label {
	// this class is used as a package of label data (name & line number) so the
	// fields are public

	String name;
	int lineNumber;

	public Label(String name, int lineNumber) {
		super();
		this.name = name;
		this.lineNumber = lineNumber;
	}

}