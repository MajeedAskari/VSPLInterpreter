import java.util.Scanner;

import compiler.Compiler;

public class Commander {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		while(true){
			
			String command = sc.nextLine();
			String[] splitCommand = command.split(" ");
			if(splitCommand[0].equals("myVSPLcompiler")){
				if(splitCommand[1].equals("-h")){
					// showHelp(); TODO
				} else {
					Compiler cmp = new Compiler(splitCommand[1]);
				}
			} else {
				System.out.println("Wrong command");
			}
		}
	}

}
