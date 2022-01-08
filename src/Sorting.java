import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Yueqiao Chen
 * @version 1.0
 * @userid ychen3221
 * @GTID 903531127
 *
 * Collaborators: N/A
 *
 * Resources: canvas
 */
public class Sorting {

    /**
     * Implement insertion sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new java.lang.IllegalArgumentException("the array the null or comparator is null");
        } else {
            for (int i = 1; i < arr.length; i++) {
                int j = i - 1;
                while (j >= 0 && comparator.compare(arr[j], arr[j + 1]) > 0) {
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    j--;
                }
            }
        }
    }

    /**
     * Implement cocktail sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * NOTE: See pdf for last swapped optimization for cocktail sort. You
     * MUST implement cocktail sort with this optimization
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void cocktailSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new java.lang.IllegalArgumentException("the array the null or comparator is null");
        } else {
            boolean swapMade = true;
            int startIndex = 0;
            int endIndex = arr.length - 1;
            int lastSwap = 0;
            while (swapMade) {
                swapMade = false;
                for (int i = startIndex; i < endIndex; i++) {
                    if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                        swap(arr, i, i + 1);
                        swapMade = true;
                        lastSwap = i;
                    }
                }
                endIndex = lastSwap;
                if (swapMade) {
                    swapMade = false;
                    for (int i = lastSwap; i > startIndex; i--) {
                        if (comparator.compare(arr[i], arr[i - 1]) < 0) {
                            swap(arr, i, i - 1);
                            swapMade = true;
                            lastSwap = i;
                        }
                    }
                }
                startIndex = lastSwap;
            }
        }
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: If two data are equal when merging, think about which subarray
     * you should pull from first
     *
     * @param <T>        data type to sort
     * @param arr        the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new java.lang.IllegalArgumentException("the array the null or comparator is null");
        } else {
            if (arr.length <= 1) {
                return;
            } else {
                int length = arr.length;
                int midIndex = length / 2;
                T[] leftArray = (T[]) new Object[midIndex];
                T[] rightArray = (T[]) new Object[length - midIndex];
                for (int i = 0; i < length; i++) {
                    if (i < midIndex) {
                        leftArray[i] = arr[i];
                    } else {
                        rightArray[i - midIndex] = arr[i];
                    }
                }
                mergeSort(leftArray, comparator);
                mergeSort(rightArray, comparator);
                int j = 0;
                int k = 0;
                while (j < leftArray.length && k < rightArray.length) {
                    if (comparator.compare(leftArray[j], rightArray[k]) <= 0) {
                        arr[j + k] = leftArray[j];
                        j++;
                    } else {
                        arr[j + k] = rightArray[k];
                        k++;
                    }
                }
                while (j < leftArray.length) {
                    arr[j + k] = leftArray[j];
                    j++;
                }
                while (k < rightArray.length) {
                    arr[j + k] = rightArray[k];
                    k++;
                }
            }
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots. For example if you
     * need a pivot between a (inclusive) and b (exclusive) where b > a, use
     * the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * If your recursion uses an inclusive b instead of an exclusive one,
     * the formula changes by adding 1 to the nextInt() call:
     *
     * int pivotIndex = rand.nextInt(b - a + 1) + a;
     *
     * It should be:
     * in-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not receive
     * credit if you do not use the one we have taught you!
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand       the Random object used to select pivots
     * @throws java.lang.IllegalArgumentException if the array or comparator or
     *                                            rand is null
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null || comparator == null || rand == null) {
            throw new java.lang.IllegalArgumentException("the array the null or comparator is null or rand is null");
        } else {
            quickSortHelper(arr, comparator, rand, 0, arr.length - 1);
        }
    }

    /**
     * Helper method of quick sort.
     *
     * @param arr the array that must be sorted after the method runs
     * @param comparator the array that must be sorted after the method runs
     * @param rand the Random object used to select pivots
     * @param start start index
     * @param end end index
     * @param <T> data type to sort
     */
    private static <T> void quickSortHelper(T[] arr, Comparator<T> comparator, Random rand, int start, int end) {
        if (end - start < 1) {
            return;
        } else {
            int pivotIndex = rand.nextInt(end - start + 1) + start;
            swap(arr, pivotIndex, start);
            int i = start + 1;
            int j = end;
            while (i <= j) {
                while (i <= j && comparator.compare(arr[i], arr[start]) <= 0) {
                    i++;
                }
                while (i <= j && comparator.compare(arr[j], arr[start]) >= 0) {
                    j--;
                }
                if (i <= j) {
                    swap(arr, i, j);
                    i++;
                    j--;
                }
            }
            swap(arr, start, j);
            quickSortHelper(arr, comparator, rand, start, j - 1);
            quickSortHelper(arr, comparator, rand, j + 1, end);
        }
    }

    /**
     * Swap values of index i and index j.
     *
     * @param arr the array that must be sorted after the method runs
     * @param i the first index i
     * @param j the second index j
     * @param <T> data type to sort
     */
    private static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(kn)
     *
     * And a best case running time of:
     * O(kn)
     *
     * You are allowed to make an initial O(n) passthrough of the array to
     * determine the number of iterations you need. The number of iterations
     * can be determined using the number with the largest magnitude.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use ArrayList or LinkedList if you wish, but it may only be
     * used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with other sorts. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * @param arr the array to be sorted
     * @throws java.lang.IllegalArgumentException if the array is null
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new java.lang.IllegalArgumentException("array is null");
        } else {
            LinkedList<Integer>[] buckets = new LinkedList[19];
            for (int i = 0; i < 19; i++) {
                buckets[i] = new LinkedList<>();
            }
            int mod = 10;
            int div = 1;
            boolean cont = true;
            while (cont) {
                cont = false;
                for (int num : arr) {
                    int bucket = num / div;
                    if (bucket / 10 != 0) {
                        cont = true;
                    }
                    if (buckets[bucket % mod + 9] == null) {
                        buckets[bucket % mod + 9] = new LinkedList<>();
                    }
                    buckets[bucket % mod + 9].add(num);
                }
                int arrIdx = 0;
                for (LinkedList<Integer> bucket : buckets) {
                    if (bucket != null) {
                        for (int num : bucket) {
                            arr[arrIdx++] = num;
                        }
                        bucket.clear();
                    }
                }
                div *= 10;
            }
        }
    }

    /**
     * Implement heap sort.
     *
     * It should be:
     * out-of-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * Use java.util.PriorityQueue as the heap. Note that in this
     * PriorityQueue implementation, elements are removed from smallest
     * element to largest element.
     *
     * Initialize the PriorityQueue using its build heap constructor (look at
     * the different constructors of java.util.PriorityQueue).
     *
     * Return an int array with a capacity equal to the size of the list. The
     * returned array should have the elements in the list in sorted order.
     *
     * @param data the data to sort
     * @return the array with length equal to the size of the input list that
     * holds the elements from the list is sorted order
     * @throws java.lang.IllegalArgumentException if the data is null
     */
    public static int[] heapSort(List<Integer> data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("data cannot be null");
        } else {
            PriorityQueue<Integer> heap = new PriorityQueue<>(data);
            int[] arr = new int[data.size()];
            int index = 0;
            while (!heap.isEmpty() && index < arr.length) {
                arr[index] = heap.remove();
                index++;
            }
            return arr;
        }
    }
}
