public class Generator
{
     
     public static void main(String args[])
     {
          int p=8209;
          int q=16411;
          int n=p*q;
          int theta=(p-1)*(q-1);
          int e=17;
          int d=0;
          int k=1;
          do
          {
             d=(k*theta+1)/e;
             if((k*theta+1)%e == 0)
               break;
             k++;
          }while(d<n);
          System.out.println("n is "+n);
          System.out.println("Private key is "+d);
     }
}
