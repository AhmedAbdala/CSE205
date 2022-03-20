/************************************************************************************************
 * CLASS: Main (Main.java)
 *
 * DESCRIPTION
 * A description of the contents of this file.
 *
 * COURSE AND PROJECT INFORMATION
 * CSE205 Object Oriented Programming and Data Structures, Spring 2022 - Session B.
 * Project Number: 1
 *
 * AUTHOR: Ahmed ABDALLA, 1223819974, aabdal16@asu.edu
 ************************************************************************************************/

package P1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
// Not used for processing, only for debugging before shipping software
// import java.util.Arrays;


public class Main {

    public static void main(String[] args) {
        Main mainObject = new Main();
        mainObject.run();
    }

    private void run () {

        int RUNS_UP = 1;
        int RUNS_DN = -1;
        try {

            ArrayList<Integer> list, listRunsUpCount, listRunsDnCount, listRunsCount;
            list = readInputFile("p1-in.txt");
            listRunsUpCount = findRuns(list, RUNS_UP);
            listRunsDnCount = findRuns(list, RUNS_DN);
            listRunsCount = mergeLists(listRunsUpCount, listRunsDnCount);
            writeOutputFile("p1-runs.txt", listRunsCount);
            // Add debugger to make sure that the file is parsed correctly.
            // System.out.println(Arrays.toString(list.toArray()));

        } catch (FileNotFoundException pException) {
            System.out.println("Oops, could not open 'p1-in.txt' for reading. The program is ending.");
            System.exit(-100);
        }
        catch (IOException pException) {
            System.out.println("Oops, could not open 'p1-runs.txt' for writing. The program is ending.");
            System.exit(-200);
        }

    }

    private ArrayList<Integer> findRuns(ArrayList<Integer> pList, int pDir) {

        int i = 0;
        int k = 0;
        ArrayList<Integer> listRunsCount;
        listRunsCount = arrayListCreate(pList.size(), 0);

        while (i < pList.size() - 1 ) {
            if (pDir == 1 && pList.get(i) <= pList.get(i + 1) ) {
                k++;
            }
            else if (pDir == -1 && pList.get(i) >= pList.get(i + 1)) {
                k++;
            }
            else {
                if ( k != 0) {
                    int originalElementValue = listRunsCount.get(k);
                    listRunsCount.set(k, originalElementValue + 1);
                    k = 0;
                }
            }
            i++;
        }
        if (k != 0) {
            int originalElementValue = listRunsCount.get(k);
            listRunsCount.set(k, originalElementValue + 1);
        }
        // Debugger, Comment out before shipping software
        // System.out.print("The listRunsCount Array is: ");
        // System.out.println(Arrays.toString(listRunsCount.toArray()));

        return listRunsCount;
    }


    private ArrayList<Integer> mergeLists(ArrayList<Integer> pListRunsUpCount, ArrayList<Integer> pListRunsDnCount) {

        ArrayList<Integer> listRunsCount;
        listRunsCount = arrayListCreate(pListRunsUpCount.size(),0);

        for (int i = 0; i < pListRunsUpCount.size(); i++) {

            int sum = pListRunsUpCount.get(i) + pListRunsDnCount.get(i);;
            listRunsCount.set(i, sum);
        }

        return listRunsCount;
    }

    private ArrayList<Integer> arrayListCreate(int pSize, int pInitValue) {

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < pSize; ++i) {
            list.add(pInitValue);
        }

        return list;
    }

    private void writeOutputFile(String pFilename, ArrayList<Integer> pListRuns) throws IOException {

        File outFile = new File(pFilename);
        PrintWriter out = new PrintWriter(outFile);

        int pListRunsTotal = 0;
        for (int i = 0;  i < pListRuns.size(); i++) {
            pListRunsTotal += pListRuns.get(i);
        }
        out.println("runs_total: " + pListRunsTotal + "\n");

        for (int k = 1; k < pListRuns.size(); k++) {

            out.println("runs_" + k +": " + pListRuns.get(k) + "\n");
        }

        out.close();
    }

    private ArrayList<Integer> readInputFile(String pFilename) throws FileNotFoundException {

        ArrayList<Integer> plist = new ArrayList<>();
        Scanner in = new Scanner (new File(pFilename));

        while (in.hasNextInt()) {
            plist.add (in.nextInt());
        }
        in.close();

        return plist;
    }

}