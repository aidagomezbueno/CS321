
/**
 * @author aidagomezbuenoberezo
 *
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class HashTest {

	private static double load_factor;
	private static int table_size;
	private static int n;
	private static int debug;

	public static void main(String[] args){
		table_size = findTwins(95500, 96000)[1];
		load_factor = Double.parseDouble(args[1]);
		n = setNKeys(table_size);
		if (args.length == 2) {
			try {
				debug = 0;
				chooseSource(Integer.parseInt(args[0]));
			} catch (NumberFormatException e) { 
				usage();
				System.exit(1);
			}
		} else if (args.length == 3) { 
			try {
				debug = Integer.parseInt(args[2]);
				if(debug!= 0 && debug!=1) {
					usage();
					System.exit(1);
				}
				chooseSource(Integer.parseInt(args[0]));
			} catch (NumberFormatException e) { 
				usage();
				System.exit(1);
			}
		} else { 
			usage();
			System.exit(1);
		}

	}
	
	private static void chooseSource(int index_source) {
		
		if(index_source==1) {
			randomSource(n, table_size, debug);
		}else if(index_source==2) {
			dateSource(n, table_size, debug);
		}else if(index_source==3) {
			fileSource(n, table_size, debug);
		}else {
			usage();
			System.exit(1);
		}
	}
	
	
	private static int setNKeys(int table_size) {
		int n = 0;
		if (load_factor < 1 && load_factor > 0) {
			n = (int) (Math.ceil(load_factor * table_size));
		} else {
			System.out.println("Load factor should be a number between 0 and 1.");
			usage();
			System.exit(1);
		}
		return n;
	}

	private static void generalPrint(String data, int tableSize) {
		System.out.println( "A good table size is found: " + tableSize);
		System.out.println("Data source type: " + data);
		System.out.println();
	}

	private static void usage() {
		System.out.println("java HashTest <input type> <load factor> [<debug level>]");
		System.out.println("\tinput type = 1 data generated using random integers, 2 dates, 3 from file word-list");
		System.out.println("\tdebug = 0 - print summary of experiment on the console");
		System.out.println("\tdebug = 1 - print summary and also the hash tables with number of duplicates and number of probes into two files");
	}

	private static void randomSource(int n_keys, int tableSize, int debug){
		LinearProbing<Integer> linearProbingTable = new LinearProbing<>(tableSize);
		DoubleHashing<Integer> doubleHashingTable = new DoubleHashing<>(tableSize);
		Random rand = new Random();
		int intRand;
		while (linearProbingTable.getTotalInserts() < n_keys || doubleHashingTable.getTotalInserts() < n_keys) {
			intRand = rand.nextInt();
			linearProbingTable.hashInsert(intRand);
			doubleHashingTable.hashInsert(intRand);
		}
		generalPrint("random number", tableSize);
		if (debug == 0) {
			zeroDebug(linearProbingTable, "Linear");
			zeroDebug(doubleHashingTable, "Double");
		} else if (debug == 1) { // If debug level is one
			oneDebug(linearProbingTable, "Linear", tableSize);
			oneDebug(doubleHashingTable, "Double", tableSize);
		} 
	}

	private static void dateSource(int n_keys, int tableSize, int debug){
		LinearProbing<Date> linearProbingTable = new LinearProbing<>(tableSize); 
		DoubleHashing<Date> doubleHashingTable = new DoubleHashing<>(tableSize);
		long current = new Date().getTime();
		while (linearProbingTable.getTotalInserts() < n_keys || doubleHashingTable.getTotalInserts() < n_keys) {
			current++;
			Date date = new Date(current);
			linearProbingTable.hashInsert(date);
			doubleHashingTable.hashInsert(date);
		}
		generalPrint("date", tableSize);
		if (debug == 0) {
			zeroDebug(linearProbingTable, "Linear");
			zeroDebug(doubleHashingTable, "Double");
		} else if (debug == 1) { // If debug level is one
			oneDebug(linearProbingTable, "Linear", tableSize);
			oneDebug(doubleHashingTable, "Double", tableSize);
		} 
	}

	private static void fileSource(int n_keys, int tableSize, int debug){
		LinearProbing<String> linearProbingTable = new LinearProbing<>(tableSize);
		DoubleHashing<String> doubleHashingTable = new DoubleHashing<>(tableSize);
		try {
			File file = new File("word-list");
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine()) {
				String word = scan.nextLine();
				if (linearProbingTable.getTotalInserts() < n_keys || doubleHashingTable.getTotalInserts() < n_keys) {
					linearProbingTable.hashInsert(word);
					doubleHashingTable.hashInsert(word);
				} else { 
					break;
				}
			}
			scan.close();
			generalPrint("word-list", tableSize);
			//DEBUG LEVELS
			if (debug == 0) {
				zeroDebug(linearProbingTable, "Linear");
				zeroDebug(doubleHashingTable, "Double");
			} else if (debug == 1) { 
				oneDebug(linearProbingTable, "Linear", tableSize);
				oneDebug(doubleHashingTable, "Double", tableSize);
			} 
		} catch (IOException e) { 
			System.out.println("File not Found");
			usage();
			System.exit(1);
		}
	}

	private static <T> void zeroDebug(HashTable<T> hashTable, String tableType) {
		double probes = ((double) hashTable.getTotalProbes()) / (double) (hashTable.getTotalInserts());
		int totalInput = hashTable.getTotalInserts() + hashTable.getTotalDuplicates();
		System.out.println("Using " + tableType + " Hashing....");
		System.out.println("Input " + totalInput + " elements, of which " + hashTable.getTotalDuplicates() + " duplicates");
		System.out.println("load factor = " + load_factor + ", Avg. no. of probes " + probes + "\n");
	}

	private static <T> void oneDebug(HashTable<T> hashTable, String tableType, int tableSize) {
		zeroDebug(hashTable, tableType);
		String fileName = tableType.toLowerCase() + "-dump";
		PrintWriter out;
		try {
			out = new PrintWriter(new FileWriter(fileName));
			for (int i = 0; i < tableSize; i++) {
				if (hashTable.getObject(i) != null) {
					out.println("table[" + i + "]: " + hashTable.getObject(i).toString());
				}
			}
			out.close();
		} catch (IOException e) { 
			System.out.println("Cannot Open file");
			usage();
			System.exit(1);
		}
	}
	
	public static int[] findTwins(int minVal, int maxVal) {
        for(int i = minVal; i < maxVal; i++){
            if(isPrime(i) && isPrime(i + 2)){
                int[] output = {i, i+2};
                return output;
            }
        }
        return null;
    }

    public static boolean isPrime(int input){
        return squareMultiply(input) == 1 && squareMultiply(input) == 1;
    }

    public static int squareMultiply(int input){
        int base = (int) new Random().nextInt(input);
        int output = base;
        String binary = Integer.toBinaryString(input-1);
        for(int i = 1; i < binary.length(); i++){
            if(binary.charAt(i) == '1'){
                output = (int) Math.abs(((Math.pow(output, 2) % input) * base) % input);
            } else {
                output = (int) (Math.abs(Math.pow(output, 2) % input));
            }
        }
        return (int)output;
    }
}