package uz.pdp.moduleservice.test;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        int[] arr = {3, 4, 5, 1, 8, 9};
        //          {3, 4, 5, 1, 8, 9}
        // temp:
//        insertSort(arr);
        Product[] productList = {new Product(1, "car", 3000.0),
                new Product(1, "car", 3000.0),
                new Product(2,"car",6000.0),
                new Product(3,"car",2000.0),
                new Product(5,"car",9000.0),
                new Product(6,"car",1000.0),
                new Product(7,"car",7000.0),
                new Product(8,"car",38000.0),
                new Product(9,"car",8000.0),
                new Product(10,"car",13000.0),
                new Product(11,"car",32000.0),
                new Product(12,"car",31000.0),};
        System.out.println(Arrays.toString(productList));
        insertProduct(productList);
        System.out.println("-------------------");
        System.out.println(Arrays.toString(productList));
    }

    private static void insertProduct(Product[] productList) {
        for (int i = 1; i < productList.length; i++) {
            int j = i - 1;
            Product temp = productList[i];
            while (j >= 0 && productList[j].compareTo(temp)>0) {
                productList[j + 1] = productList[j];
                j--;
            }
            productList[j + 1] = temp;
        }
    }

    private static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            int temp = arr[i];
            while (j >= 0 && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }

    private static void bubbleSort(int[] arr, int n){
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < n - 1; i++)
        {
            swapped = false;
            for (j = 0; j < n - i - 1; j++)
            {
                if (arr[j] > arr[j + 1])
                {
                    // swap arr[j] and arr[j+1]
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            if (swapped == false)
                break;
        }
    }
}
