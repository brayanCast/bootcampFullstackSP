package SolucionMiniReto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Challenge1 {
	
	public static String pointA(String type) {
		// metodo random longitud determinada java
		String resultado = null;
		int number = 0;
		
		if(type == "Tipo A") {
			resultado = "54";
			//System.out.print("54");
		}
		
		else if(type == "Tipo B"){
			resultado = "08";
			//System.out.print("08");
		}
			
		for(int i = 1; i<=8; i++) {
			number = (int)(Math.random()*10);
			//System.out.print(number);
			resultado += number;
		}
		return resultado;
	}
	public static boolean pointB(String identification, List<String> lista) {
		
		// Uso de condicional para saber si el usuario esta en la lista o no
		if(lista.contains(identification)) {
			//System.out.println("Sorry, but you are not on the list"); 
			return false;
		}
		else {
			//System.out.println("You are on the list");
			return true;
		}
	}
	
	public static void main(String[] args) {
		
		//Challenge1.pointA("Tipo A");
		System.out.println(Challenge1.pointA("Tipo A"));
	
		//Solicitud de numero de identificacion 
		Scanner sc = new Scanner(System.in);
		System.out.println("Write the ID number ");
		String identification = sc.next();
		
		// Creacion de una lista con numeros de identificacion
		List<String> lista = new ArrayList<>();
		lista.add("123456");
		lista.add("56789");
		lista.add("987654321");
		lista.add("123456789");
		lista.add("0123456789");
		lista.add("543210");
		lista.add("10112022");
		lista.add("11112022");
		lista.add("12112022");
		lista.add("13112022");
		
		System.out.println(Challenge1.pointB(identification, lista));
		
	}

}
