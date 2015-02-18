import java.util.HashMap;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map.Entry;


public class Huff {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		if(args.length != 1)
			System.out.println("Only 1 argument is accepted");
		else
			huffman(args[0]);
	}

	private static void huffman(String inputFile) throws Exception {
		IO.Compressor io ;
		io = new IO.Compressor(inputFile);
		char[] carray = io.giveArray();
		io.done();
		if(carray.length == 1){
			System.out.println("Invalid input: No character is found\n");
		}
		if(carray.length == 2){
			System.out.println("Actual length of the file by Huffman coding is\n1");
			System.out.printf("and minimum achievable is\n0.00");
			return;
		}
		HashMap<Character, Double> probability = new HashMap<Character, Double>();

		for (int i = carray.length-2; i >=0; i--){
			if(probability.containsKey(carray[i]))
				probability.put(carray[i],  probability.get(carray[i]) + 1);
			else 
				probability.put(carray[i], 1.0);
		}
		double temp;
		HashSet<HuffmanNode> nodeBag = new HashSet<HuffmanNode>();
		for(Entry<Character, Double> s : probability.entrySet()){
			temp = s.getValue()/(carray.length-1);
			s.setValue(temp);
			nodeBag.add(new HuffmanNode(null,null,null,temp,s.getKey(), true));
		}
		
			HuffmanNode root = buildTree(nodeBag);
			HashMap<Character, Integer> depth = new HashMap<Character, Integer>();
			calculateDepth(root, depth, 0);
			int totalLength = 0;
			for (int i = carray.length-2; i >=0; i--){
				totalLength += depth.get(carray[i]);
			}
			
			System.out.println("Actual length of the file by Huffman coding is\n" + totalLength);
			double minimum = 0;
			for(Entry<Character, Double> s : probability.entrySet()){
				minimum -= s.getValue()*Math.log(s.getValue())/Math.log(2);
			}
			System.out.printf("and minimum achievable is\n%.2f\n", minimum*(carray.length-1));
		
			
		


	}
	private static void calculateDepth(HuffmanNode node, HashMap<Character, Integer> depthHash, int depth) {
		if(node != null){
			if(node.isLeaf){
				depthHash.put(node.ch, depth);
			}else{
				calculateDepth(node.getLeft(), depthHash, depth+1);
				calculateDepth(node.getRight(), depthHash, depth+1);
			}
		}
	}

	private static HuffmanNode buildTree(HashSet<HuffmanNode> nodeBag) {

		while(nodeBag.size() > 1){
			HuffmanNode left = Collections.min(nodeBag);
			nodeBag.remove(left);
			HuffmanNode right = Collections.min(nodeBag);
			nodeBag.remove(right);
			HuffmanNode parent = new HuffmanNode(null,left,right,left.getValue()+right.getValue(),(char)0, false);
			left.setParent(parent);
			right.setParent(parent);
			nodeBag.add(parent);
		}
		return nodeBag.iterator().next();
	}



	static class HuffmanNode implements Comparable<HuffmanNode>{
		private HuffmanNode parent;
		private HuffmanNode left;
		private HuffmanNode right;
		private double value;
		private char ch;
		private boolean isLeaf;

		public HuffmanNode(HuffmanNode parent, HuffmanNode left, HuffmanNode right, double value, char ch, boolean isLeaf){
			this.parent = parent;
			this.left = left;
			this.right = right;
			this.value = value;
			this.ch = ch;
			this.isLeaf = isLeaf;
		}
		public HuffmanNode getParent(){
			return parent;
		}
		public HuffmanNode getLeft(){
			return left;
		}
		public HuffmanNode getRight(){
			return right;
		}
		public double getValue(){
			return value;
		}
		public char getChar(){
			return ch;
		}
		public boolean isLeaf(){
			return this.isLeaf;
		}
		public void setParent(HuffmanNode node){
			parent = node;
		}
		public void setLeft(HuffmanNode node){
			left = node;
		}
		public void setRight(HuffmanNode node){
			right = node;
		}
		public void setValue(double value){
			this.value = value;
		}
		@Override
		public int compareTo(HuffmanNode o) {
			return (new Double(this.value)).compareTo(o.value);
		}

	}

}
