package compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Compiler {

	ArrayList<String> alol = new ArrayList<>();
	SymbolTable symbolTable = new SymbolTable();
	LabelTable labelTable = new LabelTable();

	int begin = 0, end = 0;

	public Compiler(String fileName) {
		File file = new File(fileName);

		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				alol.add(sc.nextLine().trim());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		phase1();
		phase2();
	}

	public void phase1() {

		for (int i = 0; i < alol.size(); i++) {

			try {
				String[] splitedLine = alol.get(i).split(" ");

				// check if is commented or not
				if (splitedLine.length > 0 && splitedLine[0].length() > 1
						&& !(splitedLine[0].charAt(0) == '/' && splitedLine[0].charAt(1) == '/')) {

					// check ";"
					String lastSplit = splitedLine[splitedLine.length - 1];
					if (lastSplit.charAt(lastSplit.length() - 1) != ';') {
						throw new Exception("';' Expected");
					}
					// variable declare
					if (splitedLine[0].equals("int")) {
						String tmp = alol.get(i).trim();
						tmp = tmp.substring(4, tmp.length());
						String[] vars = tmp.split(",");
						for (String string : vars) {
							String varName = string.trim();
							symbolTable.add(trim(varName), "int", 0);
						}
					} else if (splitedLine[0].equals("float")) {
						String tmp = alol.get(i).trim();
						tmp = tmp.substring(6, tmp.length() - 1);
						String[] vars = tmp.split(",");
						for (String string : vars) {
							String varName = string.trim();
							symbolTable.add(trim(varName), "float", 0);
						}
					} else {

						switch (splitedLine[0]) {
						case "jump":
						case "input":
						case "output":
						case "outputl":
						case "outputl;":
						case "outputmessage":
						case "NOP;":
						case "jumpl":
						case "jumplc":
							break;
						case "label": {
							labelTable.add(trim(splitedLine[1]), i);
						}
						case "begin;": {
							begin = i;
							break;
						}
						case "end;": {
							end = i;
							break;
						}
						default: {
							if (symbolTable.contains(splitedLine[0])) {

								// TODO x = ... to x= ...
							} else {
								throw new Exception("command " + splitedLine[0] + " not found");
							}
						}
						}
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				System.exit(1);
			}
		}

	}

	public void phase2() {
		for (int i = begin + 1; i < end; i++) {

			try {
				String[] splitedLine = alol.get(i).split(" ");

				// check if is commented or not
				if (splitedLine.length > 0 && splitedLine[0].length() > 0
						&& !(splitedLine[0].charAt(0) == '/' && splitedLine[0].charAt(1) == '/')) {

					switch (splitedLine[0]) {
					case "jump": {
						i = Integer.parseInt(trim(splitedLine[1])) - 1;
						continue;
					}
					case "NOP;":
						break;
					case "label": {
						labelTable.add(trim(splitedLine[1]), i);
						break;
					}
					case "jumpl": {
						i = labelTable.getLine(trim(splitedLine[1])) - 1;
					}
					case "input": {
						String varName = trim(splitedLine[1]);
						if (varName != null) {
							symbolTable.setValue(varName, new Scanner(System.in).nextFloat());
						}
						break;
					}
					case "output": {
						String varName = trim(splitedLine[1]);
						if (symbolTable.getType(varName).equals("int"))
							System.out.print((int) symbolTable.getValue(varName));
						else
							System.out.print(symbolTable.getValue(varName));
						break;
					}
					case "outputl;": {
						System.out.println();
						break;
					}
					case "outputl": {
						String varName = trim(splitedLine[1]);
						if (symbolTable.getType(varName).equals("int"))
							System.out.print((int) symbolTable.getValue(varName));
						else
							System.out.print(symbolTable.getValue(varName));

						break;
					}
					case "outputmessage": {
						String[] ls = alol.get(i).split("\"");
						System.out.print(ls[1]);
						break;
					}
					default: {
						if (alol.get(i).contains("=")) {
							String[] assign = alol.get(i).split("=");
							symbolTable.setValue(assign[0].trim(), evaluate(trim(assign[1])));
						}
					}
					}

				}
			} catch (Exception ex) {
				ex.printStackTrace();
				System.exit(1);
			}
		}
	}

	private String trim(String s) { // trim ";"
		if (s.charAt(s.length() - 1) == ';')
			return s.substring(0, s.length() - 1);
		return s;
	}

	private float evaluate(String s) {
		String tmp = "", op = "-+*/";
		String[] expression = s.split("[\\-+*/]");
		for (int i = 0; i < expression.length; i++) {
			try {
				String val = Float.toString(symbolTable.getValue(expression[i]));
				expression[i] = val;
			} catch (Exception e) {
			}
		}

		int i = 0;
		for (int j = 0; j < expression.length; j++) {
			tmp += expression[j];
			for (; i < s.length(); i++) {
				if (op.contains(s.substring(i, i + 1))) {
					tmp += s.charAt(i);
					i++;
					break;
				}
			}
		}
		ExpressionCalculator calc = new ExpressionCalculator();
		return (float) calc.infix(tmp);
	}


}
