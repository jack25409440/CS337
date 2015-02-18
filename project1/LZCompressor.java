import java.util.HashMap;


public class LZCompressor {
	private int counter;
	private LZDictTreeNode root;
	
	public LZCompressor(){
		root = new LZDictTreeNode(0); // (0, "")
		counter = 0;
	}
	
	public void indexString(char[] carray, IO.Compressor io) throws Exception{
		int index = 0;
		char temp;
		LZDictTreeNode node;
		while (index < carray.length){
			node = root;
			temp = carray[index];
			while(node.hasChild(temp)){
				node = node.getChild(temp);
				temp = carray[++index];
			}
			node.appendChild(temp, ++counter);
			io.encode(node.getIndex(), temp);
			index++; 
                        if(temp=='#')
                         break;
		}
	}
	
	
	private class LZDictTreeNode {
		private HashMap<Character, LZDictTreeNode> children;
		int index;
		
		public LZDictTreeNode(int index){
			this.children = new HashMap<Character, LZDictTreeNode>();
			this.index = index;
		}
		public int getIndex(){
			return index;
		}
		public boolean hasChild(char ch){
			return children.containsKey(ch);
		}
		public LZDictTreeNode getChild(char ch){
			return children.get(ch);
		}
		public void appendChild(char ch, int index){
			children.put(ch, new LZDictTreeNode(index));
		}
	}
}
