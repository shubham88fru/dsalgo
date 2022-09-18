package misc;

import java.util.Arrays;

//Given two arrs of times of arrival
//and departures of guests, need to find a
//time slot for us such that if we go to the party
//in that time, we meet to max no. of guests.
//We have a time slot of only 20mins.
//eg: arr[] = {900, 940}
//    dep[] = {1000, 1030}
//in above eg, 2 guests, guest1 comes at
//900hrs and leaves at 1000hrs, while second guest
//arrives at 940hrs and leaves at 1030hrs.
//It's a whole day party so arrival and dep
//time can be between [0, 2359]
public class MeetingMaxGuests {

    int maxGuestsNaive(int[] arr, int[] dep) {
        int res = 0;
        for (int i=0; i<arr.length; i++) {
            int curr = 1;
            for (int j=i+1; j<arr.length; j++) {
                if (arr[i]>=arr[j] && arr[i]<=dep[j]
                        && arr[j]>=arr[i] && arr[j]<=dep[i]
                ) {
                    curr++;
                }
            }
            res = Math.max(res, curr);
        }
        return res;
    }

    int maxGuests(int[] arr, int[] dep) {
        Arrays.sort(arr);
        Arrays.sort(dep);
        int i=1, j=0, res=0, curr=1, n=arr.length;
        while (i<n && j<n) {
            if (arr[i]<=dep[j]) {
                curr++;
                i++;
            } else {
              curr--;
              j++;
            }
            res = Math.max(res, curr);
        }
        return res;
    }

    public static void main(String[] args) {
        MeetingMaxGuests meetingMaxGuests
                = new MeetingMaxGuests();
        int[] arr = {900, 600, 700};
        int[] dep = {1000, 800, 730};
        System.out.println(
                meetingMaxGuests
                        .maxGuestsNaive(arr, dep)
        );

        System.out.println("-------------");
        System.out.println(
                meetingMaxGuests
                        .maxGuests(arr, dep)
        );
    }
}
