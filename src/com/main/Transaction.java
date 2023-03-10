package com.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;


public class Transaction {
	
	public static void addTransaction(String codeSeat) throws IOException{
		FileWriter fileOutput = new FileWriter("src/com/database/transaction.txt",true);
        BufferedWriter bufferOutput = new BufferedWriter(fileOutput);
        String formattedDateTime = Helper.dateCurrent();
        
        String[] keywords = {codeSeat+","+"Sold"+","+formattedDateTime};
        
        boolean isExistTransaction = checkTransactionDatabase(keywords, false);
        if(!isExistTransaction) {
        	bufferOutput.write(codeSeat+","+"Sold"+","+formattedDateTime);
            bufferOutput.newLine();
            bufferOutput.flush();
            System.out.println("Tambah Transaksi: "+codeSeat);
        }
        bufferOutput.close();
	}
	
	public static boolean checkTransactionDatabase(String[] keywords, boolean isDisplay) throws IOException{
		FileReader fileInput = new FileReader("src/com/database/transaction.txt");
        BufferedReader bufferInput = new BufferedReader(fileInput);
        String data = bufferInput.readLine();
        boolean isExistTransaction = false;
        while (data != null) {
            String[] splitData = data.split(",");
            if (splitData[0].equals(keywords[0])) {
                isExistTransaction = true;
                break;
            }

            data = bufferInput.readLine();
        }
        bufferInput.close();

        // Menampilkan output jika diperlukan
        if (isDisplay) {
            if (isExistTransaction) {
                System.out.println("Data sudah ada di dalam file.");
            } else {
                System.out.println("Data belum ada di dalam file.");
            }
        }

        return isExistTransaction;
	}
	
	public static void updateTransaction(String oldData, String newData) throws IOException {
		File file = new File("src/com/database/transaction.txt");
	    BufferedReader reader = new BufferedReader(new FileReader(file));
	    String line;
	    StringBuilder content = new StringBuilder();

	    while ((line = reader.readLine()) != null) {
	        String[] splitData = line.split(",");
	        if (splitData[0].equals(oldData)) {
	            line = newData;
	        }
	        content.append(line).append("\n");
	    }
	    reader.close();

	    // Menulis konten yang telah diubah ke dalam file
	    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
	    writer.write(content.toString());
	    writer.close();
	}
	

	public static void transactionReport() throws IOException, ParseException {
		String transactionFile = "src/com/database/transaction.txt";
        String seatFile = "src/com/database/seats.txt";

        int totalSold = 0;
        int totalAvailable = 0;
        String transactionOutput = "";
        
        // Read seat data
        FileReader seatFileInput = new FileReader(seatFile);
        BufferedReader seatBufferInput = new BufferedReader(seatFileInput);
        String seatData = seatBufferInput.readLine();
        while (seatData != null) {
            String[] seatSplitData = seatData.split(",");
            if (seatSplitData[1].equals("Sold")) {
                totalSold++;
            } else {
                totalAvailable++;
            }
            seatData = seatBufferInput.readLine();
        }
        seatBufferInput.close();
        
        // Read transaction data
        FileReader transactionFileInput = new FileReader(transactionFile);
        BufferedReader transactionBufferInput = new BufferedReader(transactionFileInput);
        String transactionData = transactionBufferInput.readLine();
        while (transactionData != null) {
            String[] transactionSplitData = transactionData.split(",");
            if (transactionSplitData[1].equals("Cancel")) {
                // Skip cancel transaction
                transactionData = transactionBufferInput.readLine();
                continue;
            }

//            
            String formattedDate = Helper.convertFormatDete(transactionSplitData);

            transactionOutput += transactionSplitData[0] + ", " + formattedDate + "\n";
            transactionData = transactionBufferInput.readLine();
        }
        transactionBufferInput.close();

        // Output report
        String headLine = "=================== Laporan Transaksi ===================\n";
        String childHeadLine = "Total " + totalSold + " kursi terjual dan " + totalAvailable + " kursi tersedia.\n";
        String detail = "Transaksi:\n";
        System.out.println(headLine);
        System.out.println(childHeadLine);
        System.out.println(detail);
        System.out.println(transactionOutput);
        createReportTransaction(headLine, childHeadLine, detail, transactionOutput);
        
        
	}
	
	public static void createReportTransaction(String headLine, String childHeadLine, String detail, String transactionOutput) throws IOException {
		String fileName = "src/com/report/laporan-transaksi-" + Helper.dateCurrent() + ".txt";
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(headLine);
        fileWriter.write(childHeadLine);
        fileWriter.write(detail);
        fileWriter.write(transactionOutput);
        fileWriter.close();
	}
	
}
