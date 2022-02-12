import java.util.Arrays;

public class Main {
    static final int size = 10;
    static final int h = size / 2;

    public static void main(String[] args) {
        float[] arr = new float[size];

        ArrayPlus(arr);
        firstMethod(arr);
        secondMethod(arr);


        long time = System.currentTimeMillis();
        System.out.println("Первая нить = " + Arrays.toString(arr));

        System.out.println(System.currentTimeMillis() - time);

        System.out.println("Вторая нить = " + Arrays.toString(arr));
        System.out.println(System.currentTimeMillis() - time);
    }

    private static void ArrayPlus(float[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i] = 1.0f;
        }

    }

    private static void firstMethod(float []a ) {
        for (int i = 0; i < size; i++) {
            a[i] = (float) (a[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

        }
    }

    private static void secondMethod(float []a ) {
        for (int i = 0; i < size; i++) {
            a[i] = (float) (a[i] * Math.sin((0.2f + (i + h) / 5)) * Math.cos(0.2f + (i + h) / 5) * Math.cos(0.4f + (i + h) / 5));

        }
    }

    private static void secondMethod2(float[] a, int h) {
        float[] a1 = new float[h];
        float[] a2 = new float[h];

        System.arraycopy(a, 0, a1, 0, h);
        System.arraycopy(a, h, a2, 0, h);

        System.out.println("a1: " + Arrays.toString(a1));
        System.out.println("a2: " + Arrays.toString(a2));

        new Thread(() -> Main.secondMethod2(a1, 0)).start();
        new Thread(() -> Main.secondMethod2(a2, h)).start();

        System.out.println("a1 сейчас " + Arrays.toString(a1));
        System.out.println("a2 сейчас " + Arrays.toString(a2));

        System.arraycopy(a1, 0, a, 0, h);
        System.arraycopy(a2, 0, a, h, h);

    }

    private static boolean compareArrays(float[] a1, float[] a2) {
        for (int i = 0; i < a1.length; i++) {
            if (a1[i] != a2[i])
                return false;
        }
        return true;
    }


}



