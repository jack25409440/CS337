import java.io.*;

public class Encrypt
{
     static int n,publicKey;
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
            Encrypt en=new Encrypt();
            en.readKey(args[1]);
            File outputFile=new File("encrypted");
            if(!outputFile.exists())
                 outputFile.createNewFile();
            FileOutputStream fos=new FileOutputStream(outputFile);
            while(fis.available()>0)
            {
                int a=fis.read();
                //System.out.println(a);
                   a= a<<16;
                if(fis.available()>0)
                {
                   a+=(fis.read() << 8);
                }
                if(fis.available()>0)
                {
                   a+=fis.read();
                }
               int encryptedNumber=en.encryption(a);
               //System.out.print(encryptedNumber);
               en.writeFile(encryptedNumber,fos);
            }
            fos.close();
            fis.close();
          }catch(Exception e)
          {
              System.out.println(e);
          }

          
          
     }


     int encryption(int a)
     {
           //System.out.println(Integer.toBinaryString(a));
           //System.out.println(a);
               int numBit=0;
               int e=publicKey;
               while(e!=0)
               {
                  numBit++;
                  e= e>>>1;
               }
               int bits[]= new int[numBit];
               for(int i=0;i<numBit;i++)
                   bits[i]=((0x1<<i)&publicKey)>>>i;
               long encrypted=1L;
               for(int i=bits.length-1;i>=0;i--)
               {
                     if(bits[i]==0)
                       encrypted=(encrypted*encrypted)%n;
                     else
                       encrypted=(((encrypted*encrypted)%n)*a)%n;
               }
               return (int)encrypted;
     }


     void readKey(String file)
     {
           try
           {
               FileReader read = new FileReader(file);
               BufferedReader br = new BufferedReader(read);
               n=Integer.valueOf(br.readLine());
               publicKey=Integer.valueOf(br.readLine());
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
             char a= (char)((num & 0xFF000000)>>>24);
             char b= (char)((num & 0xFF0000)>>>16);
             char c= (char)((num & 0xFF00)>>>8);
             char d= (char)(num & 0xFF);
             fos.write(a);
             fos.write(b);
             fos.write(c);
             fos.write(d);
         }catch(Exception e)
         {
             System.out.println(e);
         }
     }


}
