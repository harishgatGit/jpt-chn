
public class StringPool {

	public static void main(String[] args) {
		String s1="hello";
		String s2="hello";
		String s3="hello";
		String s4=new String("hello");
		String s5=s4.intern();
		String s6=s5.intern();

		System.out.println(s1==s2);
		System.out.println(s1==s3);
		
		System.out.println(s1==s4);
		
		System.out.println(s4==s5);
		System.out.println(s5==s6);
		
		System.out.println(s1==s6);
	}

}
