package com.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Seat {
	
	public static void addSeat(String labelSeat, Integer countSeat) throws IOException{
		FileWriter fileOutput = new FileWriter("src/com/database/seats.txt",true);
        BufferedWriter bufferOutput = new BufferedWriter(fileOutput);
        
        for(int i = 1; i <= countSeat; i++) {
        	String codeSeat = labelSeat+i;
        	String[] keywords = {codeSeat};
        	
        	boolean isExistSeat = checkSeatDatabase(keywords, false);
        	
        	if (!isExistSeat){
        		bufferOutput.write(codeSeat + "," + "Free");
                bufferOutput.newLine();
                bufferOutput.flush();
                System.out.println("Tambah Denah: "+codeSeat);
        	} 
        	
        }
        bufferOutput.close();
        
	}
	public static void reportSeat() throws IOException {
		FileReader fileReader = new FileReader("src/com/database/seats.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String headLine ="=================== Denah Status ===================\n";
        System.out.println(headLine);
        
        String fileName = "src/com/report/laporan-denah-" + Helper.dateCurrent() + ".txt";
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(headLine);

        String data;
        while ((data = bufferedReader.readLine()) != null) {
            String[] splitData = data.split(",");
            System.out.println(splitData[0] + " - " + splitData[1]);
            fileWriter.write(splitData[0] + " - " + splitData[1]+"\n");
        }
        fileWriter.close();
        

        bufferedReader.close();
	}
	
	public static void cancelBookSeat(String codeSeat) throws IOException{
		String oldData = codeSeat+","+"Sold";
		String[] keywords = {oldData};
		boolean isExistSeat = checkSeatDatabase(keywords, false);
		if(isExistSeat) {
			String formattedDateTime = Helper.dateCurrent();
	        String newDataTransaksi = codeSeat+","+"Cancel"+","+ formattedDateTime;
	        String newDataSeat = codeSeat+","+"Free";
	        Transaction.updateTransaction(codeSeat, newDataTransaksi);
	        updateSeat(codeSeat, newDataSeat);
	        System.out.println("\nKursi "+codeSeat+" berhasil dibatalkan pada "+Helper.dateCurrent()+"\n");
		} else {
			System.out.println("\nTidak ada transaksi pada kursi "+codeSeat+" sebelumnya\n");
		}
	}

	public static void bookSeat(String codeSeat) throws IOException{
		String newData = codeSeat+","+"Sold";
		String[] keywords = {newData};
        boolean isExistSeat = checkSeatDatabase(keywords, false);
        if(!isExistSeat) {
        	Transaction.addTransaction(codeSeat);
        	updateSeat(codeSeat,newData);
        	System.out.println("\nKursi "+codeSeat+" berhasil dipesan pada "+Helper.dateCurrent()+"\n");
        } else {
        	System.out.println("\nKursi "+codeSeat+" Sudah di pesan Oleh Orang lain Silahkan Pilih Yang Lain\n");
        }
	}
	
	public static void updateSeat(String oldData, String newData) throws IOException {
		File file = new File("src/com/database/seats.txt");
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

	
	public static boolean checkSeatDatabase(String[] keywords, boolean isDisplay) throws IOException{
		FileReader fileInput = new FileReader("src/com/database/seats.txt");
        BufferedReader bufferInput = new BufferedReader(fileInput);
        String data = bufferInput.readLine();
        boolean isExistSeat = false;
        while (data != null) {
            String[] splitData = data.split(",");
            String[] keyw = keywords[0].split(",");
            
            
            if (keyw.length == 1 && splitData[0].equals(keyw[0])) {
                isExistSeat = true;
                break;
            } else if (keyw.length == 2 && splitData[0].equals(keyw[0]) && splitData[1].equals(keyw[1])) {
                isExistSeat = true;
                break;
            }
            

            data = bufferInput.readLine();
        }
        bufferInput.close();

        if (isDisplay) {
            if (isExistSeat) {
                System.out.println("Data sudah ada di dalam file.");
            } else {
                System.out.println("Data belum ada di dalam file.");
            }
        }

        return isExistSeat;
	}
}
