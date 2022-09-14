public class BreakContinue {
    public static void windowPosSum(int[] a, int n) {
	for (int i = 0; i < a.length; ++i) {
	    if (i == a.length-1 || a[i] < 0) {
		continue;
	    } else {
		int sum = 0;
		for (int j = i; j <= i+n; ++j) {
		    if (j >= a.length) {
			break;
		    }
		    sum += a[j];
		}
		a[i] = sum;
	    }
	}
    }

    public static void main(String[] args) {
	int[] a = {1, 2, -3, 4, 5, 4};
	int n = 3;
	windowPosSum(a, n);

	System.out.println(java.util.Arrays.toString(a));
    }
}
