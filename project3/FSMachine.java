import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * 
 * @author Jiawei Guo, Xiaohui Chen
 * 
 * This interpreter doesn't accept nondeterministic fsm
 * 
 */
public class FSMachine {
//	public static final String A = "abcdefghijklmnopqrstuvwxyz";
//	public static final String D = "0123456789";
//	public static final String N = "123456789";
//	public static final String S = "~!@#%^&*()-+{}.,";
	public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789~!@#%^&*()-+{}.,";
	public static final char[] A= {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    public static final char[] D={'0','1','2','3','4','5','6','7','8','9'};
    public static final char[] N={'1','2','3','4','5','6','7','8','9'};
    public static final char[] S={'~','!','@','#','%','^','&','*','(',')','-','+','{','}','.',','};
	
	private String name;
    private HashMap<Integer, HashMap<Character, Integer>> transitions;
    private HashSet<Integer> acceptingStates;
    private boolean isValid;
    public FSMachine(File f){
    	name = f.getName();
    	transitions = new HashMap<Integer, HashMap<Character, Integer>>();
    	transitions.put(1, new HashMap<Character, Integer>());
    	acceptingStates = new HashSet<Integer>();
    	isValid = true;
    	try {
			Scanner scan = new Scanner(f);
			if(!checkState1(scan))
				return;
			String str;
			int curstate;
        	HashMap<Character, Integer> temp;
			while(scan.hasNextLine()){
				str = scan.nextLine().trim();
				if(str.length() == 0)
					continue;
				String[] tokens = str.split("\\s");
				try{
					curstate = Integer.parseInt(tokens[0]);
				}catch(NumberFormatException e){
					System.out.println("FSM file has illegal format: (illegal state)" + name);
					isValid = false;
					return;
				}
				if(transitions.containsKey(curstate))
					temp = transitions.get(curstate);
				else{
					temp = new HashMap<Character, Integer>();
					transitions.put(curstate, temp);
				}
				processNSPAndASF(curstate, tokens, temp);
			}
			if(transitions.size() > 1 && transitions.get(1).size() == 0){
				System.out.println("There are unreachable states from state 1: " + name);
				isValid = false;
			}
		} catch (FileNotFoundException e) {
			System.out.println("IO Error while processing " + name);
			isValid = false;
		}
    }
    
    
    private boolean checkState1(Scanner scan){
			String str;
			if(scan.hasNextLine()){
				str = scan.nextLine().trim();
				if(str.charAt(0) != '1'){
					System.out.println("FSM file doesn't start with state 1: " + name);
					isValid = false;
					return false;
				}
				String[] tokens = str.split("\\s");
				return processNSPAndASF(1, tokens, transitions.get(1));
			}else{
				System.out.println("FSM file starts with empty line: " + name);
				isValid = false;
				return false;
			}
			
    }
    
    
    private boolean processNSPAndASF(int curstate, String[] tokens, HashMap<Character, Integer> temp){
		String[] nsp;
		int nextstate;
		for(int i = 1; i < tokens.length; i++){
			if(tokens[i].equals("X"))
				acceptingStates.add(curstate);
			else{
				nsp = tokens[i].split(":"); 
				if(nsp.length != 2){
					System.out.println("FSM file has illegal format (too many :'s): " + name);
					isValid = false;
					return false;
				}
				try{
					nextstate = Integer.parseInt(nsp[0]);
				}catch(NumberFormatException e){
					System.out.println("FSM file has illegal format (illegal state): " + name);
					isValid = false;
					return false;
				}
				if(!transitions.containsKey(nextstate))
					transitions.put(nextstate, new HashMap<Character, Integer>());
				if(!addTransitions(temp, nsp[1], nextstate)) {
					System.out.println("FSM file has illegal format: " + name + "Illegal NSP: " + tokens[i]);
					isValid = false;
					return false;
				}
			}
		}
		return true;
    }

	private boolean addTransitions(HashMap<Character, Integer> curstate, String string, int nextstate) {
		
		for(char c : string.toCharArray()){
			if(c == 'A'){
				if(!addGroup(curstate, A, nextstate)) return false;
			}else if(c == 'D'){
				if(!addGroup(curstate, D, nextstate)) return false;
			}else if(c == 'N'){
				if(!addGroup(curstate, N, nextstate)) return false;
			}else if(c == 'S'){
				if(!addGroup(curstate, S, nextstate)) return false;
			}else if(ALPHABET.indexOf(c) < 0) {return false;}
			if(curstate.containsKey(c) && curstate.get(c) != nextstate){
				return false; // nondeterministic fsm
			}else
				curstate.put(c, nextstate);
		}
		return true;
	}


	private boolean addGroup(HashMap<Character, Integer> curstate, char[] group, int nextstate) {
		for(char c : group)
			if(curstate.containsKey(c) && curstate.get(c) != nextstate)
				return false; // nondeterministic fsm
			else
				curstate.put(c, nextstate);
		return true;
	}
	
	
	
	public boolean isValid(){
		return this.isValid;
	}
	public String getName(){
		return this.name;
	}
	public boolean acceptString(String input){
		if(!this.isValid) {
			System.out.println("Invalid machine: " + this.name);
			return false;
		}
		HashMap<Character, Integer> transition = transitions.get(1);
		int state = 1;
		for(char c : input.toCharArray())
			if(transition.containsKey(c)){
			//	System.out.printf("From state %d to state %d with character %c\n", state, transition.get(c),c);
				transition = transitions.get(state = transition.get(c));
			}else
				return false;
			
		return acceptingStates.contains(state);
	}
	

/*==================================================================================================*/
	

	/**
	 * @param args string.txt + machine*.fsm
	 * 
	 */    	
	public static void main(String[] args) {
		if(args.length==0) {
			System.out.println("Please specify string file");
            return;
        }
		
		File strFile = new File(args[0]);
		if(!checkFile(strFile))
			return;
		
		ArrayList<String> testStrs = processStringFile(strFile);
		if(testStrs.size() == 0) {
        	System.out.println("No test string avalable");
        	return;
        }
        
        ArrayList<FSMachine> availableMachines = new ArrayList<FSMachine>();
        if(args.length==1){
        	for(int i = 1; i <= 7; i++)
        		if(checkFile(strFile = new File("machine"+i+".fsm")))
            		availableMachines.add(new FSMachine(strFile));
        }else{
	        for(int i = 1; i < args.length; i++)
	        	if(checkFile(strFile = new File(args[i])))
	        		availableMachines.add(new FSMachine(strFile));
        }
        
        
        
        if(availableMachines.size() == 0) {
        	System.out.println("No machine avalable");
        	return;
        }
        for(String test : testStrs){
        	for(FSMachine m : availableMachines){
        		if(m.acceptString(test))
        			System.out.println(m.getName() + " Accepted: " + test);
        	}
        }
        
	}


	private static ArrayList<String> processStringFile(File strFile) {
		ArrayList<String> list = new ArrayList<String>();
		try {
			Scanner scan = new Scanner(strFile);
			while(scan.hasNextLine())
				list.add(scan.nextLine().trim());
			
		} catch (FileNotFoundException e) {
			System.out.println("Error occurred during processing test file " + strFile.getName());
		}
		return list;
	}
	private static boolean checkFile(File fileName){
        if (!fileName.exists()) { 
        	System.out.println(fileName.getName() + " does not exist." + fileName.getAbsolutePath());
            return false;
        }
        if (!(fileName.isFile() && fileName.canRead())) {
        	System.out.println(fileName.getName() + " cannot be read from.");
        	return false;
        }
        return true;
    }
}
