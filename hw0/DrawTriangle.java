public class DrawTriangle {
    public static void main(String[] args) {
	int count = 5;
	for (int i = 1; i <= 5; ++i) {
	    for (int j = 1; j <= i; ++j) {
		System.out.print("*");
	    }
	    System.out.print("\n");
	}
    }
}
