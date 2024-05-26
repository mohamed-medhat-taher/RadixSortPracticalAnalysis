import java.util.Arrays;
import java.util.Random;

class Radix {

    static int getMax(int arr[], int n) {
        int mx = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > mx)
                mx = arr[i];
        return mx;
    }

    static void countSort(int arr[], int n, int exp) {
        int output[] = new int[n]; // output array
        int i;
        int count[] = new int[10];
        Arrays.fill(count, 0);

        for (i = 0; i < n; i++)
            count[(arr[i] / exp) % 10]++;

        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        for (i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        for (i = 0; i < n; i++)
            arr[i] = output[i];
    }

    static void radixsort(int arr[], int n) {
        int m = getMax(arr, n);

        for (int exp = 1; m / exp > 0; exp *= 10)
            countSort(arr, n, exp);
    }

    public static void main(String[] args) {


        // Print each element in the random array and random sorting array time
        for (int i = 0; i < 200; i++) {
            int[] sortArray = generateRandomSortedArray(200, 1000);
            long startTime = System.nanoTime();
            radixsort(sortArray, sortArray.length);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            System.out.println(duration + " ns");
        }

    }
    public static int[] generateRandomSortedArray(int size, int maxElement) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(maxElement);
        }
        Arrays.sort(array);
        return array;
    }



    public static int[] generateRandomArray(int size, int maxElement) {
        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(maxElement);
        }
        return arr;
    }


}