import java.util.*;

public class Huff
{
    static ArrayList<Node> leaves=new ArrayList<Node>();
    static ArrayList<Node> nonleaves=new ArrayList<Node>();
    public static void main(String args[])
    {
        if(args.length==1)
        {
           char fileContent[];
           Huff h=new Huff();
           try
           { 
           IO.Compressor io=new IO.Compressor(args[0]);
           fileContent=io.giveArray();
           h.compute(fileContent);
           }catch(Exception e)
           {
             System.out.println(e);
           }
           
           
        }else{
          System.out.println("Arguments are not correct. Please check.");
        }
    }

    private void compute(char[] charArray)
    {
       //System.out.println(charArray);
       //System.out.println(charArray.length-2);
       double size=charArray.length-1;
       //System.out.println(size);
       if(((int)size)==0)
       {
          System.out.println("Empty Input, please check");
          return;
       }
       if(((int)size)==1)
       {
        System.out.println("Actual length of the file by Huffman coding is\n"+(int)size);
        System.out.println("and minimum achievable length is \n"+String.format("%.2f",((int)size-1)));
        return;
       }
       HashMap<Character,Double> hash=new HashMap<Character,Double>();
       for(int i=0;i<(charArray.length-1);i++)
         {
             if(hash.containsKey(charArray[i]))
             {
               double tmp=(hash.get(charArray[i])+1.0);
               hash.put(charArray[i],tmp);
             }
             else
              {
               hash.put(charArray[i],1.0);
              }
         }

       Iterator iter = hash.entrySet().iterator(); 
       while (iter.hasNext()) { 
         Map.Entry entry = (Map.Entry) iter.next(); 
         Character key = (Character)entry.getKey(); 
         Object val = entry.getValue(); 
         double newVal=((Double)val)/size;
         //System.out.println(key+" "+newVal);
         hash.put(key,new Double(newVal));
         leaves.add(new Node(newVal,true,key));
         } 
        if(leaves.size()==1)
        {
             int tmp=0;
             System.out.println("Actual length of the file by Huffman coding is\n"+(charArray.length-1));
        System.out.println("and minimum achievable length is \n"+tmp);
        return;
        }
       buildTree(charArray);
    }

    private void buildTree(char[] charArray)
    {
       Collections.sort(leaves,new nodeSort());
       
       //System.out.println(leaves.get(0).leaf);
       //int k=0;
       while(!(leaves.isEmpty() && nonleaves.size()==1))
       {
          /**
            k++;
            for(int i=0;i<nonleaves.size();i++)
              System.out.println(k+": "+nonleaves.get(i).value);
            for(int i=0;i<leaves.size();i++)
            {
                 
                 System.out.println(k+": "+leaves.get(i).symbol+" "+leaves.get(i).value);
            }
            */
            if(nonleaves.isEmpty())
            {
               if(leaves.size()==1)
               {
                  nonleaves.add(leaves.remove(0)); 
                  break;
               }
               //System.out.println("2 leaves");
               double a=(leaves.get(0)).getValue();
               double b=(leaves.get(1)).getValue();
               Node root=new Node(a+b,false,null);
               root.setChildren(leaves.remove(0),leaves.remove(0));
               //double d=a+b;
               //System.out.println("root is "+d+" left is "+a+" right is "+b);
               nonleaves.add(root);               
            }else
            { 
                if(leaves.size()==1 && nonleaves.size()==1)
                {
                   // System.out.println("1 leave 1 nonleave");
                    double a=(leaves.get(0)).getValue();
                    double b=(nonleaves.get(0)).getValue();
                    Node root=new Node(a+b,false,null);
                    root.setChildren(leaves.remove(0),nonleaves.remove(0));
                    nonleaves.add(root);  
                    //double d=a+b;
                    
                    //System.out.println("root is "+d+" left is "+a+" right is "+b);
                }else
                {
                    if(leaves.isEmpty() && nonleaves.size()>=2)
                    {
                          //System.out.println("2 nonleaves");
                          double a=(nonleaves.get(0)).getValue();
                          double b=(nonleaves.get(1)).getValue();
                          Node root=new Node(a+b,false,null);
                          Node child1=nonleaves.remove(0);
                          Node child2=nonleaves.remove(0);
                          root.setChildren(child1,child2);
                          //double d=a+b;
                          //System.out.println("root is "+d+" left is "+a+" right is "+b);
                          nonleaves.add(root);  
                    }else
                    {   
                             
                             //System.out.println("default");
                             //System.out.println("This leave size is "+leaves.size());
                             //System.out.println("The nonleave size is "+nonleaves.size());
                             Node removedA,removedB;
                             double a1=(leaves.get(0)).getValue();
                             double a2=(nonleaves.get(0)).getValue();
                             double a=(a1>a2) ? a2:a1;
                             double b=0.0;
                             if(a1>a2)
                               removedA=nonleaves.remove(0);
                             else
                             removedA=leaves.remove(0);
                             //System.out.println("This leave size is "+leaves.size());
                             //System.out.println("The nonleave size is "+nonleaves.size());
                             if(leaves.isEmpty())
                             { 
                                b=nonleaves.get(0).getValue();
                                removedB=nonleaves.remove(0);
                             }else
                              {
                                 if(nonleaves.isEmpty())
                                  {
                                     b=leaves.get(0).getValue();
                                     removedB=leaves.get(0);
                                  }else
                                  {
                                     double b1=(leaves.get(0)).getValue();
                                     double b2=(nonleaves.get(0)).getValue();
                                     b=(b1>b2) ? b2:b1;
                                     if(b1>b2)
                                        removedB=nonleaves.remove(0);
                                     else
                                        removedB=leaves.remove(0);
                                  }
                               }
                             
                             Node root=new Node(a+b,false,null);
                             root.setChildren(removedA,removedB);
                             nonleaves.add(root);
                             //double d=a+b;
                             //System.out.println("root is "+d+" left is "+a+" right is "+b);
                    }
                    
                }
                
            }
              
       }
        
        
        calculateDepth(nonleaves.get(0));
        System.out.println(leaves.size());
        System.out.println(nonleaves.size());
       
        HashMap<Character,Integer> depHash=new HashMap<Character,Integer>();
        
        //HashMap<Character,Double> avgHash=new HashMap<Character,Double>();
        //System.out.println("Second");
        double entropy=0.0;
        for(int i=0;i<leaves.size();i++)
        {
           Node tmpNode=leaves.get(i);
           //System.out.println(tmpNode.symbol+" "+tmpNode.value);
           depHash.put(tmpNode.symbol,new Integer(tmpNode.depth));
           //avgHash.put(tmpNode.symbol,new Double(tmpNode.getValue()*tmpNode.depth));
           entropy-=(tmpNode.getValue())*((Math.log(tmpNode.getValue()))/Math.log(2));
        }
        int actLength=0;
        //double minLength=0;
        //double entropy=0;
        //System.out.println(charArray.length-2);
        for(int i=0;i<(charArray.length-1);i++)
        {
           //System.out.println(charArray[i]);
           actLength+=depHash.get(charArray[i]);
           //minLength+=avgHash.get(charArray[i]);
        }
        System.out.println(entropy);
        System.out.println("Actual length of the file by Huffman coding is\n"+actLength);
        System.out.println("and minimum achievable length is \n"+String.format("%.2f",((double)(charArray.length-1))*entropy));
     }
      
     static void calculateDepth(Node root)
     {
         if(root.left!=null)
         {
             root.left.depth=root.depth+1;
             root.right.depth=root.depth+1;
             if(root.left.isLeaf())
                leaves.add(root.left);
             if(root.right.isLeaf())
                leaves.add(root.right);
             calculateDepth(root.left);
             calculateDepth(root.right);
         }
         
     }
     

      static class Node
      {
         double value;
         Node left,right;
         boolean leaf;
         int depth;
         Character symbol;
         Node(double a,boolean leaf, Character c)
         {
               this.leaf=leaf;
               value=a;
               depth=0;
               symbol=c;
         }
         double getValue()
         {
             return value;
         }
         void setChildren(Node l,Node r)
         {
             left=l;
             right=r;
         }
         void setValue(double val)
         {
            value=val;
         }
         boolean isLeaf()
         {
            return leaf;
         }
      }



    class nodeSort implements Comparator
     {
         public final int compare(Object first,Object second)
         {
              double firstVal=((Node)first).getValue();
              double secondVal=((Node)second).getValue();
              if(firstVal>secondVal)
                 return 1;
              if(firstVal<secondVal)
                 return -1;
            

             return 0;
         }
     }
       
}




