import java.io.FileInputStream;
import java.io.ObjectInputStream;
 
public class ReadObject{
 
   public static void main (String args[]) {
 
	   Address address;
 
	   try{
 
		   FileInputStream fin = new FileInputStream("c:\\address.ser");
		   ObjectInputStream ois = new ObjectInputStream(fin);
		   address = (Address) ois.readObject();
		   ois.close();
 
		   System.out.println(address);
 
	   }catch(Exception ex){
		   ex.printStackTrace(); 
	   } 
   }
}
