package com.testing;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.xml.sax.SAXException;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.DefaultNodeMatcher;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.ElementSelectors;


public class xmlcompare {

	public static void main(String[] args) throws SAXException, IOException {



		File sFolder = new File("./xmlDest/");
		File dFolder = new File("./xmlSource");

		String[] allSourceFiles = sFolder.list();
		String[] allDestFiles = dFolder.list();

		for (String sfilename : allSourceFiles)
		{

		String dfilename="";
		try {
		dfilename = allDestFiles[Arrays.asList(allDestFiles).indexOf(sfilename)];
		} catch(Exception e)
		{
			System.out.println("File not exists in destination folder");
		}

		FileInputStream fis1 = new FileInputStream("./xmlDest/"+dfilename);
		FileInputStream fis2 = new FileInputStream("./xmlSource/"+sfilename);

		//BufferedReader source = new BufferedReader(new InputStreamReader(fis1));
		//BufferedReader target = new BufferedReader(new InputStreamReader(fis2));

		Diff diff = DiffBuilder.compare(fis1).withTest(fis2)
	            .withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byNameAndText))
	            .build();


		System.out.println("Comparing source file "+sfilename +" with dest file "+dfilename);
		System.out.println("---------------------------------------------------------------");
		for(org.xmlunit.diff.Difference d : diff.getDifferences() )
		{

			System.out.println(d);
		}
		System.out.println("---------------------------------------------------------------");
	}

	}


}
