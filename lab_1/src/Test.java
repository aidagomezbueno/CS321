import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Test {
    private static Cache c;
    private static Cache c2;
    private static int dim, dim2, NR1, NR2, NR, NH1, NH2, NH;
    private static double HR, HR1, HR2;
    static boolean cache;
    static boolean twoCache;
    static File f;
    private static long initT, exitT, runningTime;

    public static void main(String[] args) throws IOException {
        initT = 0;
        initT = System.currentTimeMillis();
        f = null;
        BufferedReader br = null;
        LinkedList<String> fr = null;
        Scanner sc = null;
        c = null;
        c2 = null;
        
        int s = args.length;
        if (s > 0) {
            f = new File(args[s - 1]); //file in last position de command line arg
            sc = new Scanner(f);
            if (s == 4) {
                twoCache = true;
                cache = false;
            } else {
                cache = true;
                twoCache = false;
            }
        } else {
            System.err.println("ERROR. Command line arguments should receive something. Cannot read file.");
        }
        
        int type = Integer.parseInt(args[0]);
        if (type <= 0) {
            System.err.println("ERROR. Only cache types 1 and 2 are allowed.");
        } else if (type == 1) { //1 cache
            dim = Integer.parseInt(args[1]);
            c = new Cache(dim); //Cache 1
            System.out.println("First level cache with " + c.getDim() + " entries has been created");
            System.out.println("..............................");
            read(f);
        } else if (type == 2) { //2 caches
            dim = Integer.parseInt(args[1]);
            dim2 = Integer.parseInt(args[2]);
            while(dim>dim2) {
            	System.out.println("\nThe size of the 2nd level cache cannot be larger than the 1st level cache is.\nYou should introduce another values.");
            	System.out.println("\nSize of 1st level cache: ");
            	Scanner scanner = new Scanner(System.in);
            	dim = scanner.nextInt();
            	//c = new Cache(scanner.nextInt());
            	System.out.println("\nSize of 2nd level cache: ");
            	dim2 = scanner.nextInt();
            	if(dim>dim2) {
            		System.out.println("\nPlease, try it again.");
            	}else {
            		c = new Cache(dim);
            		c2 = new Cache(dim2);
            	}
            	
            }
            c = new Cache(dim); //Cache 1
            c2 = new Cache(dim2); //Cache 2 levels
            System.out.println("First level cache with " + c.getDim() + " entries has been created");
            System.out.println("Second level cache with " + c2.getDim() + " entries has been created");
            System.out.println("..............................");
            read(f);
        } else { 
            System.err.println("ERROR. Only cache types 1 and 2 are allowed.");
        }
        
        //consider size of caches. java Test 2 100 200 Encyclopedia.txt
        int sizec1 = Integer.parseInt(args[1]);
        int sizec2 = Integer.parseInt(args[2]);
        
        
        sc.close();
    }

    private static void read(File f) throws FileNotFoundException {
        exitT = 0;
        NR1 = 0;
        NR2 = 0;
        NR = 0;
        NH1 = 0;
        NH2 = 0;
        NH = 0;
        HR1 = 0;
        HR2 = 0;
        HR = 0;
        try {
            Scanner sc = new Scanner(f);
            String w, l; //linea y palabras de linea
            while (sc.hasNext()) {
                l = sc.nextLine();
                StringTokenizer st = new StringTokenizer(l);
                while (st.hasMoreTokens()) {
                    w = st.nextToken(); //w es lo que vamos a comprobar qu eexista en cada cache
                    if (twoCache) {
                        NR1++;
                        if (c.existsItem(w)) {
                            NH1++;
                            c.existedItem(w);
                            c2.existedItem(w);
                        } else {
                            NR2++;
                            if (c2.existsItem(w)) {
                                NH2++;
                                c2.existedItem(w);
                                if (c.getNEntries() < c.getDim()) {
                                    c.existedItem(w);
                                } else {
                                    c.removeLast();
                                    c.addItem(w);
                                }
                            } else {
                                if (c2.getNEntries() < c2.getDim()) {
                                    c2.addItem(w);
                                } else {
                                    c2.removeLast();
                                    c2.addItem(w);
                                }
                                if (c.getNEntries() < c.getDim()) {
                                    c.addItem(w);
                                } else {
                                    c.removeLast();
                                    c.addItem(w);
                                }
                            }
                        }
                    } else {
                        NR1++;
                        if (c.existsItem(w)) {
                            NH1++;
                            c.existedItem(w);
                        } else {
                            if (c.getNEntries() < c.getDim()) {
                                c.addItem(w);
                            } else {
                                c.removeLast();
                                c.addItem(w);
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        exitT = System.currentTimeMillis();
        runningTime = exitT - initT;
        createReadme();
        printStatistics();
    }

    public static void createReadme() {
        String intro = "\n\nI am Aida Gomezbueno and this is my program that simulates a MRU cache with option to be of 2 different levels. I found it an interesting program where you play with different data items and record the number of references, by memories and global, as well as hits and the final calculation of hits over references (hit ratio), the rate at which the application has requested a data and was already stored in cache.";
        String rmS = "\nElapsed time to run the program: " + runningTime + " milli-seconds.";
        try {
            FileWriter fw = new FileWriter(new File("README.txt"));
            fw.write(rmS);
            fw.write(intro);
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void printStatistics() {
        //CALCULATION FROM REFERENCES, HITS AND HIT RATIOS
        NR = NR1;
        NH = (NH1 + NH2);
        if (NR != 0) {
            HR = (float) NH / NR;
        }
        if (NR1 != 0) {
            HR1 = (float) NH1 / NR1;
        }
        if (NR2 != 0) {
            HR2 = (float) NH2 / NR2;
        }
        //PRINT OF RESULTS IN CONSOLE
        System.out.println("The number of global references: " + NR);
        System.out.println("The number of global cache hits: " + NH);
        System.out.println("The global hit ratio                  : " + HR + "\n");
        if (c2 != null) {
            System.out.println("The number of 1st-level references: " + NR1);
            System.out.println("The number of 1st-level cache hits: " + NH1);
            System.out.println("The 1st-level cache hit ratio             : " + HR1 + "\n");
            System.out.println("The number of 2nd-level references: " + NR2);
            System.out.println("The number of 2nd-level cache hits: " + NH2);
            System.out.println("The 2nd-level cache hit ratio             : " + HR2);
        }
    }
}

