package strvr.binarysearch;

//@link - https://practice.geeksforgeeks.org/problems/allocate-minimum-number-of-pages0937/1?utm_source=gfg&utm_medium=article&utm_campaign=bottom_sticky_on_article
public class AllocateMinNumOfPages {
    public int findPages(int[]A,int N,int M) {
        //No soln possible if num of books
        //less than num of students, since not all
        //students will be able to get a book in such case.
        if (N < M) return -1;

        return binarySearch(A, N, M);
    }

    private int binarySearch(int[] A, int N, int M) {
        int start = 0;
        int end = sumArray(A); //sum of all pages will be the max possible ans (when only 1 student)

        int answer = -1;

        while (start <= end) {
            int mid = (start + (end-start)/2);

            //If partition possible, store the ans
            //and continue searching for a smaller value (by moving to left half.)
            if(isPossible(A, N, M, mid)) {
                answer = mid;
                end = mid - 1;
            } else {
                //if not possible, then move right and increase
                //the max page limit.
                start = mid + 1;
            }
        }

        return answer;
    }

    private boolean isPossible(int[] pages, int books, int students, int limit) {
        int currentSum = 0;
        int currentStudent = 1;

        for (int currentBookPageCount: pages) {
            //Only such distribution is valid (to minimize the num of pages with each student)
            //in which page count for a student is less than the limit. So, at any point if the
            //page count goes above the limit, then immediately return false.
            if (currentBookPageCount > limit) return false;

            //else if adding pages of the new book to current student's sum,
            //still keeps the pages under the limit, incorporate the book for
            //current student.
            if (currentSum + currentBookPageCount <= limit) {
                currentSum += currentBookPageCount;
            } else {
                //otherwise, we need to assign the book to next student.
                //num of pages with this new student becomes the num of
                //pages in curr book.
                currentStudent += 1;
                currentSum = currentBookPageCount;

                //if student count goes beyond the num of students
                //we have, means, we still have books pending but have
                //exhausted alll students, so distribution not possible
                //with the given limit.
                if (currentStudent > students) return false;
            }
        }

        //if here, means, the books were divided using less than or equal
        //to available students.
        return true;
    }

    private int sumArray(int[] A) {
        int sum = 0;

        for (int num: A) {
            sum += num;
        }

        return sum;
    }
}
