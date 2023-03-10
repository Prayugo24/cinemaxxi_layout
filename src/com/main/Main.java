package com.main;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.ParseException;




public class Main {
	public static void main(String[] args) throws IOException, ParseException {
		Scanner inputMenu = new Scanner(System.in);
		String labelSeat, inputUser, codeSeat;
		Integer countSeat;
		boolean isNextStep1 = true, isNextStep2 = true;
		System.out.println("\n=================== Selamat Datang (Cinema XXI), Silahkan masukkan konfigurasi denah "
				+ "studio ===================\n\n");
		
		try {
	        System.out.println("Masukkan Label Kursi: ");
	        labelSeat = inputMenu.next();
	        while(isNextStep1) {
	        	System.out.println("Masukkan Jumlah Kursi: ");
		        countSeat = inputMenu.nextInt();
		        if(countSeat <= 20) {
		        	isNextStep1 = false;
		        	Seat.addSeat(labelSeat, countSeat);
		        } else {
		        	System.out.println("\nJumlah Kursi Maksimal 20: \n");	
		        }
	        }
	        
	        while (isNextStep2) {
	        	System.out.println("\n=================== Pilih salah satu menu di bawah ini ===================");
	        	System.out.println("1.\tPemesanan Kursi");
	            System.out.println("2.\tPembatalan Kursi");
	            System.out.println("3.\tLaporan Denah");
	            System.out.println("4.\tLaporan Transaksi");
	            System.out.println("5.\tKeluar");
	            System.out.print("\n\nMasukkan pilihan Anda: ");
	            
	            inputUser = inputMenu.next();
	            switch (inputUser) {
	            	case "1":
	            		System.out.print("\nMasukkan kode kursi yang ingin dipesan: ");
	            		codeSeat = inputMenu.next();
	            		Seat.bookSeat(codeSeat);
	            	break;
	            	case "2":
	            		System.out.print("\nMasukkan kode kursi yang ingin dibatalkan: ");
	            		codeSeat = inputMenu.next();
	            		Seat.cancelBookSeat(codeSeat);
	            	break;
	            	case "3":
	            		Seat.reportSeat();
	            	break;
	            	case "4":
	            		Transaction.transactionReport();
	            	break;
	            	case "5":
	            		isNextStep2 = false;
	            	break;
	            	default:
	                    System.err.println("\nInput anda tidak ditemukan\nSilahkan pilih [1-5]");
	            }
	        }
	        System.out.print("\nTerimakasih :) ");
	    } catch (InputMismatchException e) {
	        System.out.println("Input tidak valid");
	        return;
	    }
	}
	
}
