package com.altitconsulting.helpdev.file.csv;

import org.junit.Test;

import com.altitconsulting.helpdev.file.csv.CSVReader;

import junit.framework.TestCase;

public final class CSVReaderTest extends TestCase {
		
	@Test
	public void testCSVReader() {
		final String path = getClass().getResource("CSVReaderTest.csv").getPath();
		final CSVReader reader = new CSVReader(path);
		reader.read();		
	}

	 @Test
	   public void testCSVReaderFindDelimeter() {
		 final String semicolon = ";";
		 final String quote = ",";
		
	     assertEquals(semicolon, CSVReader.findDelimeter("name;age;adress;length;size;other"));
	     assertEquals(quote, CSVReader.findDelimeter("file,composition,stuff,tool,measure"));
	     assertEquals(quote, CSVReader.findDelimeter("file,composition_Ok,stuff,tool-Pass,measure"));
	      
	     try {
		     assertEquals(semicolon, CSVReader.findDelimeter("name;age,adress,length,size;other"));
		     fail("Expected an IllegalArgumentException to be thrown");
	     } catch (IllegalArgumentException iAException) {
	    	 assertEquals(iAException.getMessage(), "Unable to detect csv separator");
	     }
	      try {
		     assertEquals(quote, CSVReader.findDelimeter("file,composition;stuff;tool;measure"));
	    	 fail("Expected an IllegalArgumentException to be thrown");
	      } catch (IllegalArgumentException iAException) {
	    	 assertEquals(iAException.getMessage(), "Unable to detect csv separator");
	      }
	      try {
		     assertEquals(semicolon, CSVReader.findDelimeter("name,age;adress;length;size;other"));
	    	 fail("Expected an IllegalArgumentException to be thrown");
	      } catch (IllegalArgumentException iAException) {
	    	 assertEquals(iAException.getMessage(), "Unable to detect csv separator");
	      }
	      try {
		     assertEquals(quote, CSVReader.findDelimeter("file,composition,stuff,tool;measure"));
	    	 fail("Expected an IllegalArgumentException to be thrown");
	      } catch (IllegalArgumentException iAException) {
	    	 assertEquals(iAException.getMessage(), "Unable to detect csv separator");
	      }	      
	   }
}