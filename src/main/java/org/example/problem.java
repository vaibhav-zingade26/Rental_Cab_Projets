package org.example;

public class problem {
    public static void main(String[] args) {
        int a[]={1,2,3,4,5,6,7,8,9};
        int evenNumber=0;
        int oddNumber=0;

        for (int i=0;i<a.length;i++){
            if(a[i]%2==0){
                evenNumber=evenNumber+1;
            }else {
                oddNumber=oddNumber+1;
            }
        }
        System.out.println("Even Number= "+evenNumber);
        System.out.println("Odd Number= "+oddNumber);
    }

}
