package com.main;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Helper {

	public static String dateCurrent() throws IOException {
		LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm");
        String formattedDateTime = currentDateTime.format(formatter);
        return formattedDateTime;
	} 
	
	public static String convertFormatDete(String[] transactionSplitData) throws IOException, ParseException {
		DateFormat inputDateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
        DateFormat outputDateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");
        Date transactionDate = inputDateFormat.parse(transactionSplitData[2]);
        String formattedDate = outputDateFormat.format(transactionDate);
        return formattedDate;
	}
}
