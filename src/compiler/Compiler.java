package compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Compiler {

	ArrayList<String> alol = new ArrayList<>();
	SymbolTable symbolTable = new SymbolTable();

	public Compiler(String fileName) {
		File file = new File(fileName);

		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				alol.add(sc.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void Phase1() {

		for (int i = 0; i < alol.size(); i++) {

			try {
				String[] splitedLine = alol.get(i).split(" ");

				// check if is commented or not
				if (!(splitedLine[0].charAt(0) == '/' && splitedLine[0].charAt(1) == '/')) {

					// check ";"
					String lastSplit = splitedLine[splitedLine.length - 1];
					if (lastSplit.charAt(lastSplit.length() - 1) != ';') {
						throw new Exception("';' Expected");
					}
					// variable declare TODO
					if (splitedLine[0].equals("int") || splitedLine[0].equals("float")) {

					}

					switch (splitedLine[0]) {
					case "jump": {
						i = Integer.parseInt(splitedLine[1]) - 1;
						continue;
					}
					case "input": {
						String varName = splitedLine[1];
						if (varName != null) {
							symbolTable.setValue(varName, new Scanner(System.in).nextFloat());
						}
						break;
					}
					case "output": {
						String varName = splitedLine[1];
						System.out.print(symbolTable.getValue(varName));
						break;
					}
					case "outputl": {
						String varName = splitedLine[1];
						if (varName == null)
							System.out.println();
						else
							System.out.print(symbolTable.getValue(varName));
						break;
					}
					case "outputmessage": {
						String[] ls = alol.get(i).split("\"");
						System.out.print(ls[1]);
					}
					}

				}
			} catch (Exception ex) {
				ex.printStackTrace();
				System.exit(1);
			}
		}

	}
	
	public void phase2(){
		for (int i = 0; i < alol.size(); i++) {

			try {
				String[] splitedLine = alol.get(i).split(" ");

				// check if is commented or not
				if (!(splitedLine[0].charAt(0) == '/' && splitedLine[0].charAt(1) == '/')) {

					switch (splitedLine[0]) {
					case "jump": {
						i = Integer.parseInt(splitedLine[1]) - 1;
						continue;
					}
					case "input": {
						String varName = splitedLine[1];
						if (varName != null) {
							symbolTable.setValue(varName, new Scanner(System.in).nextFloat());
						}
						break;
					}
					case "output": {
						String varName = splitedLine[1];
						System.out.print(symbolTable.getValue(varName));
						break;
					}
					case "outputl": {
						String varName = splitedLine[1];
						if (varName == null)
							System.out.println();
						else
							System.out.print(symbolTable.getValue(varName));
						break;
					}
					case "outputmessage": {
						String[] ls = alol.get(i).split("\"");
						System.out.print(ls[1]);
					}
					}

				}
			} catch (Exception ex) {
				ex.printStackTrace();
				System.exit(1);
			}
		}
	}

	private String getS(String s){
		String tmp = "";
		for(int i = 0; i <s.length();i++){
			if
		}
	}

}
