import java.io.*;

public class Decrypt
{
    static int n,privateKey;
    public static void main(String args[])
    {
        if(args.length!=2)
         {
            System.out.println("Invalid command, please try again");
            return;
         }
         File file=new File(args[0]);
         if (!file.exists()) 
         {
             System.out.println(args[0] + " does not exist.");
             return;
          }
          if (!(file.isFile() && file.canRead())) 
          {
              System.out.println(file.getName() + " cannot be read from.");
              return;
          }
         
         try
          {
            FileInputStream fis = new FileInputStream(file);
            Decrypt de=new Decrypt();
            de.readKey(args[1]);
            File outputFile=new File("decrypted");
            if(!outputFile.exists())
                 outputFile.createNewFile();
            FileOutputStream fos=new FileOutputStream(outputFile);
            while(fis.available()>0)
            {
                int a=fis.read();
                //System.out.println(a);
                if(fis.available()>0)
                {
                   a= a<<8;
                   a+=fis.read();
                }
                if(fis.available()>0)
                {
                   a=a<<8;
                   a+=fis.read();
                }
                if(fis.available()>0)
                {
                   a=a<<8;
                   a+=fis.read();
                }
               int decryptedNumber=de.decryption(a);
               de.writeFile(decryptedNumber,fos);
            }
            fos.close();
            fis.close();
          }catch(Exception e)
          {
              System.out.println(e);
          }
     }
  
     int decryption(int a)
     {
           //System.out.println(Integer.toBinaryString(a));
           //System.out.println(a);
           long m=a;
               int numBit=0;
               int e=privateKey;
               while(e!=0)
               {
                  numBit++;
                  e= e>>>1;
               }
               int bits[]= new int[numBit];
               for(int i=0;i<numBit;i++)
                   bits[i]=((0x1<<i)&privateKey)>>>i;
               long decrypted=1L;
               for(int i=bits.length-1;i>=0;i--)
               {
                     if(bits[i]==0)
                       decrypted=(decrypted*decrypted)%n;
                     else
                       decrypted=(((decrypted*decrypted)%n)*a)%n;
               }
               return (int)decrypted;
     }


     void readKey(String file)
     {
           try
           {
               FileReader read = new FileReader(file);
               BufferedReader br = new BufferedReader(read);
               n=Integer.valueOf(br.readLine());
               br.readLine();
               privateKey=Integer.valueOf(br.readLine());
               br.close();
               read.close();
            }catch(Exception e)
            {
               System.out.println(e);
            }
     }

     void writeFile(int num,FileOutputStream fos)
     {
         try
         {
             //char a= (char)((num & 0xFF000000)>>>24);
             char b= (char)((num & 0xFF0000)>>>16);
             char c= (char)((num & 0xFF00)>>>8);
             char d= (char)(num & 0xFF);
             //fos.write(a);
             fos.write(b);
             fos.write(c);
             fos.write(d);
         }catch(Exception e)
         {
             System.out.println(e);
         }
     }
     

}
