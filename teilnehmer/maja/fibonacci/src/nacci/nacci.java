package nacci;

public class nacci {

    public static int nacci(int i) {
        if (i < 3) {
            return 1;
        }
        else {

            int last = 2, lastbefor = 1;
            for (int n = 3; n < i; n++) {
                int tmp = last + lastbefor;
                lastbefor = last;
                last = tmp;
            }
            return last;
        }
    }

    public static void main(String[] args) {
        int i = 14;
        System.out.println(nacci(i));
    }
}
