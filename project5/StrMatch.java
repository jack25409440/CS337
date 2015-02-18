import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/**
 * @author Jiawei Guo, Xiaohui Chen
 *
 *
 */

public class StrMatch {

	public static void main(String[] args) {
		if(args.length!=3) {
			System.out.println("Invalid command, please try again");
			return;
		}
		File patternFile = new File(args[0]);
		File sourceFile = new File(args[1]);
		File outputFile = new File(args[2]);
		if (!patternFile.exists() || !patternFile.canRead() ||
				!sourceFile.exists() || !sourceFile.canRead() ||
				(outputFile.exists() && !outputFile.canWrite())){
			System.out.println("Invalid files, please try again");
			return;
		}

		ArrayList<String> patterns = getPatterns(patternFile);
		char[] source = getSource(sourceFile);
		String match;
		char[] p;
		//Stopwatch sw = new Stopwatch();
		try {
			PrintStream output = new PrintStream(outputFile);
			for (String pattern : patterns){
				p = pattern.toCharArray();
				//sw.start();
				match = RabinKarp(p, source) ? "MATCHED" : "FAILED";
				//sw.stop();
				//System.out.printf("RK:  %12d ns\n", sw.timeInNanoseconds());
				output.printf("RK  %s: %s\n", match, pattern);
				//sw.start();
				match = KnuthMorrisPratt(p, source) ? "MATCHED" : "FAILED"; 
				//sw.stop();
				//System.out.printf("KMP: %12d ns\n\n", sw.timeInNanoseconds());
				output.printf("KMP %s: %s\n", match, pattern);
			}
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	private static ArrayList<String> getPatterns(File patternFile) {
		ArrayList<String> result = new ArrayList<String>();
		StringBuilder patternStr = new StringBuilder();
		try {
			Scanner scan = new Scanner(patternFile);
			String temp;
			int length;

			boolean reading = false;
			while(scan.hasNextLine()){
				temp = scan.nextLine();
				length = temp.length();
				if(length == 0){
					if (reading) patternStr.append('\n');
					else continue;
				} else if(length == 1){
					if (reading) {
						if (temp.equals("&")) {
							result.add(patternStr.toString());
							reading = false;
						} else {
							patternStr.append(temp);
							patternStr.append('\n');
						}
					} else {
						if (temp.equals("&")) {
							reading = true;
							patternStr = new StringBuilder('\n');
						} else {
							System.out.println("Invalid line: " + temp);
						}
					}
				} else {
					if (reading){
						if(temp.endsWith("&")){
							patternStr.append(temp.substring(0, length -1));
							result.add(patternStr.toString());
							reading = false;
						} else {
							patternStr.append(temp);
							patternStr.append('\n');
						}
					}else{
						if(temp.endsWith("&")){
							result.add(temp.substring(1, length - 1));
						} else {
							reading = true;
							patternStr = new StringBuilder(temp.substring(1, length));
							patternStr.append('\n');
						}
					}
				}
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return result;
	}

	private static char[] getSource(File source) {
		StringBuilder result = new StringBuilder();
		try {
			Scanner scan = new Scanner(source);
			if(scan.hasNextLine())
				result.append(scan.nextLine());
			while(scan.hasNextLine()){
				result.append('\n');
				result.append(scan.nextLine());
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return result.toString().toCharArray();
	}

	public static boolean RabinKarp(char[] pattern, char[] source) {
		if(pattern.length > source.length)
			return false;
		if (pattern.length == 0)
			return true;
		final int Q = 127; //127,23,47,601
		final int BASE = 256 % Q;

		int aIndex = 0, bIndex = pattern.length;

		int patternHash = pattern[bIndex-1];
		int ar = source[bIndex-1];
		int expo = 1;
		for (int i = bIndex - 2; i >= 0; i--){
			expo = (expo*BASE) % Q;
			patternHash += ((int)pattern[i]*expo) % Q;
			ar += ((int)source[i]*expo) % Q;
		}
		patternHash = patternHash % Q;
		ar = ar % Q;
		if (ar == patternHash && compareSubArray(pattern, source, aIndex))
			return true;
		while(bIndex < source.length){
			ar = (ar - (int)source[aIndex]*expo) % Q;
			if (ar < 0) ar += Q;
			ar = (ar*BASE + (int)source[bIndex]) % Q;
			aIndex++;
			bIndex++;
			if(ar == patternHash && compareSubArray(pattern, source, aIndex))
				return true;
		}
		return false;
	}

	private static boolean compareSubArray(char[] pattern, char[] source, int aIndex) {
		for (int i = 0; i < pattern.length; i++)
			if(pattern[i] != source[i+aIndex])
				return false;
		return true;
	}

	public static boolean KnuthMorrisPratt(char[] pattern, char[] source) {
		if(pattern.length > source.length)
			return false;
		if (pattern.length == 0)
			return true;

		int[] coresOfPrefixes = precomputeCoresOfPrefixes(pattern); 

		int patternIndex = 0, sourceIndex = 0;
		while(sourceIndex < source.length && patternIndex < pattern.length){
			if(pattern[patternIndex] == source[sourceIndex]){
				patternIndex++;
				sourceIndex++;
			}else{
				if(patternIndex == 0)
					sourceIndex++;
				else
					patternIndex = coresOfPrefixes[patternIndex];
			}
		}
		return patternIndex == pattern.length;
	}

	private static int[] precomputeCoresOfPrefixes(char[] pattern) {
		int[] cores = new int[pattern.length];
		cores[0] = 0; //shouldn't be used
		cores[1] = 0;
		int i = 0, j = 1;
		while (j < cores.length - 1){
			if(pattern[i] == pattern[j]){
				cores[j+1] = i + 1;
				j = j + 1;
				i = cores[j];
			}else {
				if (i != 0)
					i = cores[i];
				else{
					cores[j+1] = 0;
					j = j + 1;
					i = 0;
				}
			}
		}
		return cores;
	}
}
