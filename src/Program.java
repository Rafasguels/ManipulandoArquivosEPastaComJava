import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		List<Product> list = new ArrayList<>();
		
		System.out.println("Enter file path: "); //Pedindo o caminho onde esta o arquivo que deve ser lido pelo sistema//
		String sourceFileStr = sc.nextLine(); //Usuario digitando o caminho//
		
		File sourceFile = new File(sourceFileStr);  //grava o caiminho da pasta pelo usuario//
		String sourceFolderStr = sourceFile.getParent(); //informando o diretorio de um arquivo//
		
				
		boolean sucess = new File(sourceFolderStr + "\\out").mkdir(); //Criando uma pasta em cima do diretorio localizado em sourceFolderStr//
																	  //Windows usae sempre \\//
			
		System.out.println("Folder created :" + sucess); //Informando que a pasta foi criada com a variavel sucess//
		
		String targetFileStr = sourceFolderStr + "\\out\\sumary.csv"; //criando um arquivo totalemnte novo//
		
		try (BufferedReader br = new BufferedReader(new FileReader(sourceFileStr))) { //Lendo o arquivo e colocando em buffer//
					// br é o nome da variavel do BufferedReader//
			
			String itemCsv = br.readLine();
			while (itemCsv != null) {
				
				String[] fields = itemCsv.split(",");
				String name = fields[0];
				double price = Double.parseDouble(fields[1]);
				int quantity = Integer.parseInt(fields[2]);
				
				list.add(new Product(name, price, quantity));
				
				itemCsv = br.readLine();
				
			}
			
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))) {
			
			for (Product item: list) {
				bw.write(item.getName() + "," + String.format("%.2f", item.total()));
				bw.newLine();
			}
			
			System.out.println(targetFileStr + " CREATED!");
			
			
		} catch (IOException e) {
			System.out.println("Error writing filee: " + e.getMessage());
		}
					
		} catch (IOException e) {
			System.out.println("Error writing filee: " + e.getMessage());
		}
		
		sc.close();
	}

}
