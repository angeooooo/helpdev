package com.altitconsulting.helpdev.file.csv;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.altitconsulting.helpdev.util.Utils;

/**
 * Class used for reading into a csv file.
 * @author ange-louistoma
 */
public final class CSVReader {
	
	private final File file;
	
    public CSVReader(final String path) {
    	this.file = new File(path);		
		if (!file.exists()) {
			throw new IllegalArgumentException(new StringBuilder("No such file or directory").append(path).toString());
		}
	}
    
    public void read() {
    	List<String> list = new ArrayList<>();

		try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()))) {

			//1. filter line 3
			//2. convert all content to upper case
			//3. convert it into a List
			list = stream
					//.filter(line -> !line.startsWith("line3"))
					//.map(String::toUpperCase)
					.collect(Collectors.toList());

		} catch (final IOException e) {
			e.printStackTrace();
		}

		list.forEach(System.out::println);
    }

	public static String findDelimeter(final String header) {	
		String result ="";		
		final Pattern pattern = Pattern.compile("[^a-zA-Z0-9\\-_]*");
		final Matcher matcher = pattern.matcher(header);
		final List<String> occurences = new ArrayList<String>();

        while(matcher.find()) {
        	String separator = matcher.group();
        	if (Utils.isNotNull(separator)) {
        		occurences.add(separator);        		
        	}        	
        }
        
        for (final String current : occurences) {
        	if (!current.equals(occurences.get(0))) {
        		throw new IllegalArgumentException(new StringBuilder("Unable to detect csv separator").toString());
        	}
        }        
        result = occurences.get(0);
        return result;
	}
}
