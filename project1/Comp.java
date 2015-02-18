import java.util.ArrayList;


public class Comp {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		if(args.length != 2)
			System.out.println("Only 2 arguments are accepted");
		else if (args[0].equals("c"))
			compressFile(args[1]);
		else if (args[0].equals("d"))
			decompressFile(args[1]);
		else
			System.out.println("Invalid flag");
	}
	private static void compressFile(String inputFile) throws Exception {
		IO.Compressor io ;
		io = new IO.Compressor(inputFile);
		new LZCompressor().indexString(io.giveArray(), io);
		io.done();
	}
	private static void decompressFile(String inputFile) throws Exception {
		ArrayList<String> list = new ArrayList<String>();
		list.add("");
		IO.Decompressor io ;
		io = new IO.Decompressor(inputFile);
		IO.pair pair = io.decode();
		while(pair.valid){
			String temp = list.get(pair.index)+pair.extension;
			list.add(temp);
			io.append(temp);
			pair = io.decode();
		}
                  io.append("");
		io.done();
	}

	

}
