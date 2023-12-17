import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SumOfPartNums {
    public static void main(String[] args){
        String fileName = "src/main/java/input.txt";

        System.out.println(findSum(fileName));
    }

    private static ArrayList<Character> symbolsArrayList = new ArrayList();

    private static String[] digitsArray = new String[]{"0","1","2","3","4","5","6","7","8","9"};

    private static ArrayList<int[]> partNumbersArrayList = new ArrayList<>();
    // each item in this ArrayList is an Array with: start index, end index, the number, line number

    private static ArrayList<int[]> star_and_num_pair_arrayList = new ArrayList<>();
    // star symbol line num, star symbol index, the number

    private static ArrayList<int[]> gearNumbersArrayList = new ArrayList<>();
    private static void addData(int partNumberLineNum, int charIndex, int thePartNumber){
        int[] tempData = new int[3];
        tempData[0] = partNumberLineNum;
        tempData[1] = charIndex;
        tempData[2] = thePartNumber;

        if(star_and_num_pair_arrayList.size()==0){
            star_and_num_pair_arrayList.add(tempData);
        }
        else {
            boolean keepGoing = true;

            while(keepGoing){

                int counter = 1;

                int temp_lineNum = star_and_num_pair_arrayList.get(star_and_num_pair_arrayList.size()-counter)[0];

                if(partNumberLineNum < temp_lineNum){
                    counter++;
                    temp_lineNum = star_and_num_pair_arrayList.get(star_and_num_pair_arrayList.size()-counter)[0];
                    while(partNumberLineNum < temp_lineNum){
                        counter++;
                        temp_lineNum = star_and_num_pair_arrayList.get(star_and_num_pair_arrayList.size()-counter)[0];
                    }
                    if(partNumberLineNum > temp_lineNum){
                        if(counter==1){
                            int index_to_swap = star_and_num_pair_arrayList.size()-counter;
                            int[] item_to_swap = star_and_num_pair_arrayList.get(index_to_swap);
                            star_and_num_pair_arrayList.remove(index_to_swap);
                            star_and_num_pair_arrayList.add(index_to_swap,tempData);
                            star_and_num_pair_arrayList.add(index_to_swap+1,item_to_swap);
                            break;
                        }
                        else if(counter>1){
                            int index_to_swap = star_and_num_pair_arrayList.size()-counter+1;
                            int[] item_to_swap = star_and_num_pair_arrayList.get(index_to_swap);
                            star_and_num_pair_arrayList.remove(index_to_swap);
                            star_and_num_pair_arrayList.add(index_to_swap,tempData);
                            star_and_num_pair_arrayList.add(index_to_swap+1,item_to_swap);
                            break;
                        }

                    }
                    else if(partNumberLineNum == temp_lineNum){
                        int counter2 = 0;
                        int temp_starSymbolIndex = star_and_num_pair_arrayList.get(star_and_num_pair_arrayList.size()-counter)[1];
                        while(charIndex < temp_starSymbolIndex && partNumberLineNum == temp_lineNum){
                            counter2++;
                            temp_starSymbolIndex = star_and_num_pair_arrayList.get(star_and_num_pair_arrayList.size()-counter-counter2)[1];
                            temp_lineNum = star_and_num_pair_arrayList.get(star_and_num_pair_arrayList.size()-counter-counter2)[0];
                        }
                        if(partNumberLineNum > temp_lineNum){
                            int index_to_swap = star_and_num_pair_arrayList.size()-counter-counter2+1;
                            int[] item_to_swap = star_and_num_pair_arrayList.get(index_to_swap);
                            star_and_num_pair_arrayList.remove(index_to_swap);
                            star_and_num_pair_arrayList.add(index_to_swap,tempData);
                            star_and_num_pair_arrayList.add(index_to_swap+1,item_to_swap);
                            break;
                        }
                        else{
                            if(charIndex < temp_starSymbolIndex){
                                int index_to_swap = star_and_num_pair_arrayList.size()-counter-counter2;
                                int[] item_to_swap = star_and_num_pair_arrayList.get(index_to_swap);
                                star_and_num_pair_arrayList.remove(index_to_swap);
                                star_and_num_pair_arrayList.add(index_to_swap,tempData);
                                star_and_num_pair_arrayList.add(index_to_swap+1,item_to_swap);
                                break;
                            }
                            if(charIndex == temp_starSymbolIndex){
                                int index_to_swap = star_and_num_pair_arrayList.size()-counter-counter2;
                                int[] item_to_swap = star_and_num_pair_arrayList.get(index_to_swap);
                                star_and_num_pair_arrayList.remove(index_to_swap);
                                star_and_num_pair_arrayList.add(index_to_swap,tempData);
                                star_and_num_pair_arrayList.add(index_to_swap+1,item_to_swap);
                                break;
                            }
                            else if(charIndex > temp_starSymbolIndex){
                                int index_to_add = star_and_num_pair_arrayList.size()-counter-counter2+1;
                                star_and_num_pair_arrayList.add(index_to_add,tempData);
                                break;
                            }
                        }
                    }
                }
                else if (partNumberLineNum == temp_lineNum){
                    int counter2 = 0;
                    int temp_starSymbolIndex = star_and_num_pair_arrayList.get(star_and_num_pair_arrayList.size()-counter)[1];
                    while(charIndex < temp_starSymbolIndex && partNumberLineNum == temp_lineNum){
                        counter2++;
                        temp_starSymbolIndex = star_and_num_pair_arrayList.get(star_and_num_pair_arrayList.size()-counter-counter2)[1];
                        temp_lineNum = star_and_num_pair_arrayList.get(star_and_num_pair_arrayList.size()-counter-counter2)[0];
                    }
                    if(partNumberLineNum > temp_lineNum){
                        int index_to_swap = star_and_num_pair_arrayList.size()-counter-counter2+1;
                        int[] item_to_swap = star_and_num_pair_arrayList.get(index_to_swap);
                        star_and_num_pair_arrayList.remove(index_to_swap);
                        star_and_num_pair_arrayList.add(index_to_swap,tempData);
                        star_and_num_pair_arrayList.add(index_to_swap+1,item_to_swap);
                        break;
                    }
                    else{
                        if(charIndex <= temp_starSymbolIndex){
                            int index_to_swap = star_and_num_pair_arrayList.size()-counter-counter2;
                            int[] item_to_swap = star_and_num_pair_arrayList.get(index_to_swap);
                            star_and_num_pair_arrayList.remove(index_to_swap);
                            star_and_num_pair_arrayList.add(index_to_swap,tempData);
                            star_and_num_pair_arrayList.add(index_to_swap+1,item_to_swap);
                            break;
                        }
                        else if(charIndex > temp_starSymbolIndex){
                            int index_to_add = star_and_num_pair_arrayList.size()-counter-counter2+1;
                            star_and_num_pair_arrayList.add(index_to_add,tempData);
                            break;
                        }
                    }

                }
                else{
                    star_and_num_pair_arrayList.add(tempData);
                    break;
                }

            }

        }

    }

    private static void checkLine_starSymbolOccurrence(boolean isFirstLine, boolean isLastLine, int partNumberStartIndex,
                                               int partNumberEndIndex, int thePartNumber, int partNumberLineNum,
                                               String line, int starSymbolOccurrence, ArrayList<String> allLines){

        if(isFirstLine){
            if(starSymbolOccurrence==0){
                // check the below line
                String belowLine = allLines.get(partNumberLineNum+1);

                if(partNumberStartIndex==0) {// the partNumber is at the BEGINNING of the line
                    for (int charIndex = partNumberStartIndex; charIndex <= partNumberEndIndex;charIndex++){
                        if(belowLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum+1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
                else if(partNumberEndIndex==line.length()-1){ // the partNumber is at the END of the line
                    for (int charIndex = partNumberStartIndex-1; charIndex < partNumberEndIndex;charIndex++){
                        if(belowLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum+1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
                else{ // the partNumber is NOT at the BEGINNING and the END of the line
                    for (int charIndex = partNumberStartIndex-1; charIndex <= partNumberEndIndex;charIndex++){
                        if(belowLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum+1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
            }
            else if(starSymbolOccurrence==1){
                // check the current line
                String currentLine = allLines.get(partNumberLineNum);

                if(partNumberStartIndex==0) {// the partNumber is at the BEGINNING of the line
                    if(currentLine.charAt(partNumberEndIndex)=='*'){
                        addData(partNumberLineNum, partNumberEndIndex, thePartNumber);
                    }
                }
                else if(partNumberEndIndex==line.length()-1) { // the partNumber is at the END of the line
                    if(currentLine.charAt(partNumberStartIndex-1)=='*'){
                        addData(partNumberLineNum, partNumberStartIndex-1, thePartNumber);
                    }
                }
                else { // the partNumber is NOT at the BEGINNING and the END of the line
                    if(currentLine.charAt(partNumberEndIndex)=='*'){
                        addData(partNumberLineNum, partNumberEndIndex, thePartNumber);
                    }
                    else if(currentLine.charAt(partNumberStartIndex-1)=='*'){
                        addData(partNumberLineNum, partNumberStartIndex-1, thePartNumber);
                    }
                }

                // check the below line
                String belowLine = allLines.get(partNumberLineNum+1);

                if(partNumberStartIndex==0) {// the partNumber is at the BEGINNING of the line
                    for (int charIndex = partNumberStartIndex; charIndex <= partNumberEndIndex;charIndex++){
                        if(belowLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum+1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
                else if(partNumberEndIndex==line.length()-1){ // the partNumber is at the END of the line
                    for (int charIndex = partNumberStartIndex-1; charIndex < partNumberEndIndex;charIndex++){
                        if(belowLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum+1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
                else{ // the partNumber is NOT at the BEGINNING and the END of the line
                    for (int charIndex = partNumberStartIndex-1; charIndex <= partNumberEndIndex;charIndex++){
                        if(belowLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum+1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
            }
            else if(starSymbolOccurrence>1){

                // check the current line
                String currentLine = allLines.get(partNumberLineNum);

                if(partNumberStartIndex==0) {// the partNumber is at the BEGINNING of the line
                    for (int charIndex = partNumberStartIndex; charIndex <= partNumberEndIndex;charIndex++){
                        if(currentLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum+1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
                else if(partNumberEndIndex==line.length()-1){ // the partNumber is at the END of the line
                    for (int charIndex = partNumberStartIndex-1; charIndex < partNumberEndIndex;charIndex++){
                        if(currentLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum+1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
                else{ // the partNumber is NOT at the BEGINNING and the END of the line
                    for (int charIndex = partNumberStartIndex-1; charIndex <= partNumberEndIndex;charIndex++){
                        if(currentLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum+1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }

                // check the below line
                String belowLine = allLines.get(partNumberLineNum+1);

                if(partNumberStartIndex==0) {// the partNumber is at the BEGINNING of the line
                    for (int charIndex = partNumberStartIndex; charIndex <= partNumberEndIndex;charIndex++){
                        if(belowLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum+1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
                else if(partNumberEndIndex==line.length()-1){ // the partNumber is at the END of the line
                    for (int charIndex = partNumberStartIndex-1; charIndex < partNumberEndIndex;charIndex++){
                        if(belowLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum+1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
                else{ // the partNumber is NOT at the BEGINNING and the END of the line
                    for (int charIndex = partNumberStartIndex-1; charIndex <= partNumberEndIndex;charIndex++){
                        if(belowLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum+1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
            }
        }
        else if(isLastLine){

            if(starSymbolOccurrence==0){
                // check the below line
                String aboveLine = allLines.get(partNumberLineNum-1);

                if(partNumberStartIndex==0) {// the partNumber is at the BEGINNING of the line
                    for (int charIndex = partNumberStartIndex; charIndex <= partNumberEndIndex;charIndex++){
                        if(aboveLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum-1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
                else if(partNumberEndIndex==line.length()-1){ // the partNumber is at the END of the line
                    for (int charIndex = partNumberStartIndex-1; charIndex < partNumberEndIndex;charIndex++){
                        if(aboveLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum-1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
                else{ // the partNumber is NOT at the BEGINNING and the END of the line
                    for (int charIndex = partNumberStartIndex-1; charIndex <= partNumberEndIndex;charIndex++){
                        if(aboveLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum-1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
            }
            else if(starSymbolOccurrence==1){

                // check the current line
                String currentLine = allLines.get(partNumberLineNum);

                if(partNumberStartIndex==0) {// the partNumber is at the BEGINNING of the line
                    if(currentLine.charAt(partNumberEndIndex)=='*'){
                        addData(partNumberLineNum, partNumberEndIndex, thePartNumber);
                    }
                }
                else if(partNumberEndIndex==line.length()-1) { // the partNumber is at the END of the line
                    if(currentLine.charAt(partNumberStartIndex-1)=='*'){
                        addData(partNumberLineNum, partNumberStartIndex-1, thePartNumber);
                    }
                }
                else { // the partNumber is NOT at the BEGINNING and the END of the line
                    if(currentLine.charAt(partNumberEndIndex)=='*'){
                        addData(partNumberLineNum, partNumberEndIndex, thePartNumber);
                    }
                    else if(currentLine.charAt(partNumberStartIndex-1)=='*'){
                        addData(partNumberLineNum, partNumberStartIndex-1, thePartNumber);
                    }
                }

                // check the above line
                String aboveLine = allLines.get(partNumberLineNum+1);

                if(partNumberStartIndex==0) {// the partNumber is at the BEGINNING of the line
                    for (int charIndex = partNumberStartIndex; charIndex <= partNumberEndIndex;charIndex++){
                        if(aboveLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum-1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
                else if(partNumberEndIndex==line.length()-1){ // the partNumber is at the END of the line
                    for (int charIndex = partNumberStartIndex-1; charIndex < partNumberEndIndex;charIndex++){
                        if(aboveLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum-1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
                else{ // the partNumber is NOT at the BEGINNING and the END of the line
                    for (int charIndex = partNumberStartIndex-1; charIndex <= partNumberEndIndex;charIndex++){
                        if(aboveLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum-1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
            }
            else if(starSymbolOccurrence>1){

                // check the current line
                String currentLine = allLines.get(partNumberLineNum);

                if(partNumberStartIndex==0) {// the partNumber is at the BEGINNING of the line
                    for (int charIndex = partNumberStartIndex; charIndex <= partNumberEndIndex;charIndex++){
                        if(currentLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
                else if(partNumberEndIndex==line.length()-1){ // the partNumber is at the END of the line
                    for (int charIndex = partNumberStartIndex-1; charIndex < partNumberEndIndex;charIndex++){
                        if(currentLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
                else{ // the partNumber is NOT at the BEGINNING and the END of the line
                    for (int charIndex = partNumberStartIndex-1; charIndex <= partNumberEndIndex;charIndex++){
                        if(currentLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum, charIndex, thePartNumber);
                            break;
                        }
                    }
                }

                // check the above line
                String aboveLine = allLines.get(partNumberLineNum-1);

                if(partNumberStartIndex==0) {// the partNumber is at the BEGINNING of the line
                    for (int charIndex = partNumberStartIndex; charIndex <= partNumberEndIndex;charIndex++){
                        if(aboveLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum-1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
                else if(partNumberEndIndex==line.length()-1){ // the partNumber is at the END of the line
                    for (int charIndex = partNumberStartIndex-1; charIndex < partNumberEndIndex;charIndex++){
                        if(aboveLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum-1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
                else{ // the partNumber is NOT at the BEGINNING and the END of the line
                    for (int charIndex = partNumberStartIndex-1; charIndex <= partNumberEndIndex;charIndex++){
                        if(aboveLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum-1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
            }
        }
        else if(!isFirstLine && !isLastLine){

            if(starSymbolOccurrence==0){
                // check the below line
                String belowLine = allLines.get(partNumberLineNum+1);

                if(partNumberStartIndex==0) {// the partNumber is at the BEGINNING of the line
                    for (int charIndex = partNumberStartIndex; charIndex <= partNumberEndIndex;charIndex++){
                        if(belowLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum+1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
                else if(partNumberEndIndex==line.length()-1){ // the partNumber is at the END of the line
                    for (int charIndex = partNumberStartIndex-1; charIndex < partNumberEndIndex;charIndex++){
                        if(belowLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum+1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
                else{ // the partNumber is NOT at the BEGINNING and the END of the line
                    for (int charIndex = partNumberStartIndex-1; charIndex <= partNumberEndIndex;charIndex++){
                        if(belowLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum+1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }

                // check the above line
                String aboveLine = allLines.get(partNumberLineNum-1);

                if(partNumberStartIndex==0) {// the partNumber is at the BEGINNING of the line
                    for (int charIndex = partNumberStartIndex; charIndex <= partNumberEndIndex;charIndex++){
                        if(aboveLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum-1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
                else if(partNumberEndIndex==line.length()-1){ // the partNumber is at the END of the line
                    for (int charIndex = partNumberStartIndex-1; charIndex < partNumberEndIndex;charIndex++){
                        if(aboveLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum-1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
                else{ // the partNumber is NOT at the BEGINNING and the END of the line
                    for (int charIndex = partNumberStartIndex-1; charIndex <= partNumberEndIndex;charIndex++){
                        if(aboveLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum-1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
            }
            else if(starSymbolOccurrence==1){

                // check the current line
                String currentLine = allLines.get(partNumberLineNum);

                if(partNumberStartIndex==0) {// the partNumber is at the BEGINNING of the line
                    if(currentLine.charAt(partNumberEndIndex)=='*'){
                        addData(partNumberLineNum, partNumberEndIndex, thePartNumber);
                    }
                }
                else if(partNumberEndIndex==line.length()-1) { // the partNumber is at the END of the line
                    if(currentLine.charAt(partNumberStartIndex-1)=='*'){
                        addData(partNumberLineNum, partNumberStartIndex-1, thePartNumber);
                    }
                }
                else { // the partNumber is NOT at the BEGINNING and the END of the line
                    if(currentLine.charAt(partNumberEndIndex)=='*'){
                        addData(partNumberLineNum, partNumberEndIndex, thePartNumber);
                    }
                    else if(currentLine.charAt(partNumberStartIndex-1)=='*'){
                        addData(partNumberLineNum, partNumberStartIndex-1, thePartNumber);
                    }
                }

                // check the above line
                String aboveLine = allLines.get(partNumberLineNum-1);

                if(partNumberStartIndex==0) {// the partNumber is at the BEGINNING of the line
                    for (int charIndex = partNumberStartIndex; charIndex <= partNumberEndIndex;charIndex++){
                        if(aboveLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum-1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
                else if(partNumberEndIndex==line.length()-1){ // the partNumber is at the END of the line
                    for (int charIndex = partNumberStartIndex-1; charIndex < partNumberEndIndex;charIndex++){
                        if(aboveLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum-1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
                else{ // the partNumber is NOT at the BEGINNING and the END of the line
                    for (int charIndex = partNumberStartIndex-1; charIndex <= partNumberEndIndex;charIndex++){
                        if(aboveLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum-1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }

                // check the below line
                String belowLine = allLines.get(partNumberLineNum+1);

                if(partNumberStartIndex==0) {// the partNumber is at the BEGINNING of the line
                    for (int charIndex = partNumberStartIndex; charIndex <= partNumberEndIndex;charIndex++){
                        if(belowLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum+1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
                else if(partNumberEndIndex==line.length()-1){ // the partNumber is at the END of the line
                    for (int charIndex = partNumberStartIndex-1; charIndex < partNumberEndIndex;charIndex++){
                        if(belowLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum+1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
                else{ // the partNumber is NOT at the BEGINNING and the END of the line
                    for (int charIndex = partNumberStartIndex-1; charIndex <= partNumberEndIndex;charIndex++){
                        if(belowLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum+1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }

            }
            else if(starSymbolOccurrence>1){

                // check the current line
                String currentLine = allLines.get(partNumberLineNum);

                if(partNumberStartIndex==0) {// the partNumber is at the BEGINNING of the line
                    for (int charIndex = partNumberStartIndex; charIndex <= partNumberEndIndex;charIndex++){
                        if(currentLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
                else if(partNumberEndIndex==line.length()){ // the partNumber is at the END of the line
                    for (int charIndex = partNumberStartIndex-1; charIndex < partNumberEndIndex;charIndex++){
                        if(currentLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
                else{ // the partNumber is NOT at the BEGINNING and the END of the line
//                    System.out.println("=========================");
//                    System.out.println("currentLine = "+currentLine);
                    for (int charIndex = partNumberStartIndex-1; charIndex <= partNumberEndIndex;charIndex++){
//                        System.out.println("=========================");
//                        System.out.println("partNumberStartIndex = "+partNumberStartIndex);
//                        System.out.println("charIndex = "+charIndex);
                        if(currentLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum, charIndex, thePartNumber);
                            break;
                        }
                    }
                }

                // check the above line
                String aboveLine = allLines.get(partNumberLineNum-1);

                if(partNumberStartIndex==0) {// the partNumber is at the BEGINNING of the line
                    for (int charIndex = partNumberStartIndex; charIndex <= partNumberEndIndex;charIndex++){
                        if(aboveLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum-1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
                else if(partNumberEndIndex==line.length()){ // the partNumber is at the END of the line
                    for (int charIndex = partNumberStartIndex-1; charIndex < partNumberEndIndex;charIndex++){
                        if(aboveLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum-1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
                else{ // the partNumber is NOT at the BEGINNING and the END of the line
                    for (int charIndex = partNumberStartIndex-1; charIndex <= partNumberEndIndex;charIndex++){
                        if(aboveLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum-1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }

                // check the below line
                String belowLine = allLines.get(partNumberLineNum+1);

                if(partNumberStartIndex==0) {// the partNumber is at the BEGINNING of the line
                    for (int charIndex = partNumberStartIndex; charIndex <= partNumberEndIndex;charIndex++){
                        if(belowLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum+1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
                else if(partNumberEndIndex==line.length()){ // the partNumber is at the END of the line
                    for (int charIndex = partNumberStartIndex-1; charIndex < partNumberEndIndex;charIndex++){
                        if(belowLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum+1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
                else{ // the partNumber is NOT at the BEGINNING and the END of the line
                    for (int charIndex = partNumberStartIndex-1; charIndex <= partNumberEndIndex;charIndex++){
                        if(belowLine.charAt(charIndex)=='*'){
                            addData(partNumberLineNum+1, charIndex, thePartNumber);
                            break;
                        }
                    }
                }
            }
        }
    }


    private static void checkLine_linePosition(boolean isFirstLine, boolean isLastLine, int partNumberStartIndex,
                                               int partNumberEndIndex, int thePartNumber, int partNumberLineNum,
                                               String line, ArrayList<String> allLines){
        int starSymbolOccurrence;

        if(isFirstLine){
            // in the line that has partNumber, there are NOT starSymbol
            if(line.indexOf("*")==-1){
                starSymbolOccurrence = 0;
            }
            // in the line that has partNumber, there is ONE starSymbol
            else if(line.indexOf("*")==line.lastIndexOf("*")){
                starSymbolOccurrence = 1;
            }
            // in the line that has partNumber, there are MULTIPLE starSymbols
            else{
                starSymbolOccurrence = 2;
            }

            checkLine_starSymbolOccurrence(true,false, partNumberStartIndex, partNumberEndIndex, thePartNumber,
                    partNumberLineNum, line, starSymbolOccurrence, allLines);

        }
        else if(isLastLine){
            // in the line that has partNumber, there are NOT starSymbol
            if(line.indexOf("*")==-1){
                starSymbolOccurrence = 0;
            }
            // in the line that has partNumber, there is ONE starSymbol
            else if(line.indexOf("*")==line.lastIndexOf("*")){
                starSymbolOccurrence = 1;
            }
            // in the line that has partNumber, there are MULTIPLE starSymbols
            else{
                starSymbolOccurrence = 2;
            }

            checkLine_starSymbolOccurrence(false,true, partNumberStartIndex, partNumberEndIndex, thePartNumber,
                    partNumberLineNum, line, starSymbolOccurrence, allLines);
        }
        else if(!isFirstLine && !isLastLine){
            // in the line that has partNumber, there are NOT starSymbol
            if(line.indexOf("*")==-1){
                starSymbolOccurrence = 0;
            }
            // in the line that has partNumber, there is ONE starSymbol
            else if(line.indexOf("*")==line.lastIndexOf("*")){
                starSymbolOccurrence = 1;
            }
            // in the line that has partNumber, there are MULTIPLE starSymbols
            else{
                starSymbolOccurrence = 2;
            }

            checkLine_starSymbolOccurrence(false,false, partNumberStartIndex, partNumberEndIndex, thePartNumber,
                    partNumberLineNum, line, starSymbolOccurrence, allLines);
        }
    }


    private static long findGearRatioSum(ArrayList<int[]> thePartNumbersArrayList, ArrayList<String> allLines) {

        // each array item of thePartNumbersArrayList has the following items:
        // quadruple[0]=startIndex;
        // quadruple[1]=endIndex;
        // quadruple[2]=theNumber;
        // quadruple[3]=partNumberLineNum;

        long sum = 0;

        BufferedReader reader;

        String line = null;

        int[] quadruple = new int[4];
        int partNumberStartIndex = -1;
        int partNumberEndIndex = -1;
        int thePartNumber = -1;
        int partNumberLineNum = -1;


        for (int partNumberIndex = 0; partNumberIndex < thePartNumbersArrayList.size(); partNumberIndex++) {

            quadruple = thePartNumbersArrayList.get(partNumberIndex);
            partNumberStartIndex = quadruple[0];
            partNumberEndIndex = quadruple[1];
            thePartNumber = quadruple[2];
            partNumberLineNum = quadruple[3];

            line = allLines.get(partNumberLineNum);

            // partNumber is in the FIRST line of the input file
            if (partNumberLineNum == 0) {

                checkLine_linePosition(true, false, partNumberStartIndex, partNumberEndIndex, thePartNumber,
                        partNumberLineNum, line, allLines);

            }
            // partNumber is in the LAST line of the input file
            else if (partNumberLineNum == allLines.size() - 1) {

                checkLine_linePosition(false, true, partNumberStartIndex, partNumberEndIndex, thePartNumber,
                        partNumberLineNum, line, allLines);

            }
            // partNumber is NOT in the FIRST and LAST line of the input file
            else {
                checkLine_linePosition(false, false, partNumberStartIndex, partNumberEndIndex, thePartNumber,
                        partNumberLineNum, line, allLines);

            }
        }

        ArrayList<int[]> masterArrayList = new ArrayList<>();

        if (star_and_num_pair_arrayList.size() == 2) {
            int[] tempArray0 = star_and_num_pair_arrayList.get(0);
            int[] tempArray1 = star_and_num_pair_arrayList.get(1);
            int tempArray0_lineNum = tempArray0[0];
            int tempArray1_lineNum = tempArray1[0];
            int tempArray0_starSymbolIndex = tempArray0[1];
            int tempArray1_starSymbolIndex = tempArray1[1];
            if (tempArray0_lineNum == tempArray1_lineNum && tempArray0_starSymbolIndex == tempArray1_starSymbolIndex) {
                sum += tempArray0[2] * tempArray1[2];
            }

        } else if (star_and_num_pair_arrayList.size() == 3) {
            int[] tempArray0 = star_and_num_pair_arrayList.get(0);
            int[] tempArray1 = star_and_num_pair_arrayList.get(1);
            int[] tempArray2 = star_and_num_pair_arrayList.get(2);
            int tempArray0_lineNum = tempArray0[0];
            int tempArray1_lineNum = tempArray1[0];
            int tempArray2_lineNum = tempArray2[0];
            int tempArray0_starSymbolIndex = tempArray0[1];
            int tempArray1_starSymbolIndex = tempArray1[1];
            int tempArray2_starSymbolIndex = tempArray2[1];

            if (tempArray0_lineNum == tempArray1_lineNum && tempArray0_starSymbolIndex == tempArray1_starSymbolIndex) {
                if (tempArray1_lineNum != tempArray2_lineNum || tempArray1_starSymbolIndex != tempArray2_starSymbolIndex) {
                    sum += tempArray0[2] * tempArray1[2];
                }
            }

            if (tempArray0_lineNum != tempArray1_lineNum || tempArray0_starSymbolIndex != tempArray1_starSymbolIndex) {
                if (tempArray1_lineNum == tempArray2_lineNum && tempArray1_starSymbolIndex == tempArray2_starSymbolIndex) {
                    sum += tempArray1[2] * tempArray2[2];
                }
            }


        }
        else if (star_and_num_pair_arrayList.size() > 3) {

            selectGearNumbers(star_and_num_pair_arrayList);


        }


//        System.out.println("=================================================================");
//        for (int index = 0; index < gearNumbersArrayList.size(); index++) {
//            System.out.println("[" + gearNumbersArrayList.get(index)[0] +
//                    "," + gearNumbersArrayList.get(index)[1] +
//                    "," + gearNumbersArrayList.get(index)[2] + "]");
//
//        }

        for(int index = 0; index<gearNumbersArrayList.size();index+=2){
            int[] arr1 = gearNumbersArrayList.get(index);
            int[] arr2 = gearNumbersArrayList.get(index+1);
//            System.out.println("sum (before add) = "+sum);
//            System.out.println("num1 = "+arr1[2]);
//            System.out.println("num2 = "+arr2[2]);
//            System.out.println("num1*num2 = "+arr1[2]*arr2[2]);
            sum += arr1[2]*arr2[2];
//            System.out.println("sum (after add) = "+sum);
        }



        return sum;
    }

    private static void selectGearNumbers(ArrayList<int[]> star_and_num_pair_arrayList){

        boolean keepGoing = true;
        int counter =0;

        ArrayList<int[]> tempArrayList = new ArrayList<>();

        while(keepGoing){

            if(counter<star_and_num_pair_arrayList.size()-1){
                int[] item1 = star_and_num_pair_arrayList.get(counter);
                int[] item2 = star_and_num_pair_arrayList.get(counter+1);
                if(item1[0]==item2[0] && item1[1]==item2[1]){
                    tempArrayList.add(item1);
                    tempArrayList.add(item2);
                }
                else{
                    if(tempArrayList.size()==2){
                        gearNumbersArrayList.add(tempArrayList.get(0));
                        gearNumbersArrayList.add(tempArrayList.get(1));
                    }
                    tempArrayList.clear();
                }
            }
            else if(counter==star_and_num_pair_arrayList.size()-1){
                if(tempArrayList.size()==2){
                    gearNumbersArrayList.add(tempArrayList.get(0));
                    gearNumbersArrayList.add(tempArrayList.get(1));
                }
            }


            counter++;
            if(counter==star_and_num_pair_arrayList.size()){
                keepGoing = false;
            }
        }




    }

    private static long findSum(String fileName){
        symbolsArrayList.add('$');
        symbolsArrayList.add('@');
        symbolsArrayList.add('!');
        symbolsArrayList.add('#');
        symbolsArrayList.add('%');
        symbolsArrayList.add('^');
        symbolsArrayList.add('&');
        symbolsArrayList.add('*');
        symbolsArrayList.add('-');
        symbolsArrayList.add('=');
        symbolsArrayList.add('+');
        symbolsArrayList.add('/');

        long sum = 0;
        String line = null;
        ArrayList<String> allLines = new ArrayList();

        BufferedReader reader;


        try{
            reader = new BufferedReader(new FileReader(fileName));

            line = reader.readLine();

            while(line != null){
                allLines.add(line);

                line = reader.readLine();
            }

            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = null;





        int lineNum = -1;

        boolean isPartNumber = false;
        boolean isGear = false;

        for(int lineIndex = 0; lineIndex<allLines.size();lineIndex++){


            if(lineIndex==0){
                // NO line above, ONLY line below
                String theLine = allLines.get(lineIndex);
                String belowLine = allLines.get(lineIndex+1);

                matcher = pattern.matcher(theLine);

                while (matcher.find()){
//                    System.out.print("Start index: " + matcher.start());
//                    System.out.print(" End index: " + matcher.end());
//                    System.out.println(" Found: " + matcher.group());

                    int startIndex = matcher.start();
                    int endIndex = matcher.end();
                    int theNumber = Integer.parseInt(matcher.group());

                    // check the Line
                    if(startIndex == 0){
                        // check after the number
                        if (endIndex < theLine.length()){
                            if(symbolsArrayList.contains(theLine.charAt(endIndex))){
                                isPartNumber = true;
                                lineNum = lineIndex;
                            }
                            if(symbolsArrayList.contains('*')){
                                isGear = true;
                            }
                        }

                    }
                    else if(startIndex > 0){
                        // check before the number
                        if(symbolsArrayList.contains(theLine.charAt(startIndex-1))){
                            isPartNumber = true;
                            lineNum = lineIndex;
                        }
                        if(symbolsArrayList.contains('*')){
                            isGear = true;
                        }

                        // check after the number
                        if (endIndex < theLine.length()){
                            if(symbolsArrayList.contains(theLine.charAt(endIndex))){
                                isPartNumber = true;
                                lineNum = lineIndex;
                            }
                            if(symbolsArrayList.contains('*')){
                                isGear = true;
                            }
                        }

                    }


                    // check line below
                    if(startIndex == 0){
                        for(int k = 0; k <= endIndex; k++){
                            if(belowLine.charAt(k)!='.'){
                                if(symbolsArrayList.contains(belowLine.charAt(k))){
                                    isPartNumber = true;
                                    lineNum = lineIndex;
                                    if(symbolsArrayList.contains('*')){
                                        isGear = true;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    else if(startIndex > 0){
                        for(int k = startIndex - 1; k <= endIndex; k++){
                            if(belowLine.charAt(k)!='.'){
                                if(symbolsArrayList.contains(belowLine.charAt(k))){
                                    isPartNumber = true;
                                    lineNum = lineIndex;
                                    if(symbolsArrayList.contains('*')){
                                        isGear = true;
                                    }
                                    break;
                                }
                            }
                        }
                    }

//                    System.out.println("isPartNumber = "+isPartNumber);

                    if(isPartNumber){
//                        sum += theNumber;
                        int[] quadruple = new int[4];
                        quadruple[0]=startIndex;
                        quadruple[1]=endIndex;
                        quadruple[2]=theNumber;
                        quadruple[3]=lineNum;

                        partNumbersArrayList.add(quadruple);
                        isPartNumber = false;
                        isGear = false;

                    }


                }


            }
            else if(lineIndex==allLines.size()-1){
                // NO line below, ONLY line above

                String theLine = allLines.get(lineIndex);
//                String belowLine = allLines.get(lineIndex+1);
                String aboveLine = allLines.get(lineIndex-1);

                matcher = pattern.matcher(theLine);

                while (matcher.find()){
//                    System.out.print("Start index: " + matcher.start());
//                    System.out.print(" End index: " + matcher.end());
//                    System.out.println(" Found: " + matcher.group());

                    int startIndex = matcher.start();
                    int endIndex = matcher.end();
                    int theNumber = Integer.parseInt(matcher.group());

                    // check the Line, after the number
                    if(symbolsArrayList.contains(theLine.charAt(endIndex))){
                        isPartNumber = true;
                        lineNum = lineIndex;
                    }
                    if(symbolsArrayList.contains('*')){
                        isGear = true;
                    }

                    // check the Line
                    if(startIndex == 0){
                        // check after the number
                        if (endIndex < theLine.length()){
                            if(symbolsArrayList.contains(theLine.charAt(endIndex))){
                                isPartNumber = true;
                                lineNum = lineIndex;
                            }
                            if(symbolsArrayList.contains('*')){
                                isGear = true;
                            }
                        }

                    }
                    else if(startIndex > 0){
                        // check before the number
                        if(symbolsArrayList.contains(theLine.charAt(startIndex-1))){
                            isPartNumber = true;
                            lineNum = lineIndex;
                        }
                        if(symbolsArrayList.contains('*')){
                            isGear = true;
                        }

                        // check after the number
                        if (endIndex < theLine.length()){
                            if(symbolsArrayList.contains(theLine.charAt(endIndex))){
                                isPartNumber = true;
                                lineNum = lineIndex;
                            }
                            if(symbolsArrayList.contains('*')){
                                isGear = true;
                            }
                        }

                    }

                    // check line above
                    if(startIndex == 0){
                        for(int k = 0; k <= endIndex; k++){
                            if(aboveLine.charAt(k)!='.'){
                                if(symbolsArrayList.contains(aboveLine.charAt(k))){
                                    isPartNumber = true;
                                    lineNum = lineIndex;
                                    if(symbolsArrayList.contains('*')){
                                        isGear = true;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    else if(startIndex > 0){
                        for(int k = startIndex - 1; k <= endIndex; k++){
                            if(aboveLine.charAt(k)!='.'){
                                if(symbolsArrayList.contains(aboveLine.charAt(k))){
                                    isPartNumber = true;
                                    lineNum = lineIndex;
                                    if(symbolsArrayList.contains('*')){
                                        isGear = true;
                                    }
                                    break;
                                }
                            }
                        }
                    }

//                    System.out.println("isPartNumber = "+isPartNumber);

                    if(isPartNumber){
//                        sum += theNumber;
                        int[] quadruple = new int[4];
                        quadruple[0]=startIndex;
                        quadruple[1]=endIndex;
                        quadruple[2]=theNumber;
                        quadruple[3]=lineNum;

                        partNumbersArrayList.add(quadruple);
                        isPartNumber = false;
                        isGear = false;

                    }


                }
            }
            else{
                // BOTH above and below lines

                String theLine = allLines.get(lineIndex);
                String belowLine = allLines.get(lineIndex+1);
                String aboveLine = allLines.get(lineIndex-1);

                matcher = pattern.matcher(theLine);

                while (matcher.find()){
//                    System.out.print("Start index: " + matcher.start());
//                    System.out.print(" End index: " + matcher.end());
//                    System.out.println(" Found: " + matcher.group());

                    int startIndex = matcher.start();
                    int endIndex = matcher.end();
                    int theNumber = Integer.parseInt(matcher.group());

                    // check the Line
                    if(startIndex == 0){
                        // check after the number
                        if (endIndex < theLine.length()){
                            if(symbolsArrayList.contains(theLine.charAt(endIndex))){
                                isPartNumber = true;
                                lineNum = lineIndex;
                            }
                            if(symbolsArrayList.contains('*')){
                                isGear = true;
                            }
                        }

                    }
                    else if(startIndex > 0){
                        // check before the number
                        if(symbolsArrayList.contains(theLine.charAt(startIndex-1))){
                            isPartNumber = true;
                            lineNum = lineIndex;
                        }
                        if(symbolsArrayList.contains('*')){
                            isGear = true;
                        }

                        // check after the number
                        if (endIndex < theLine.length()){
                            if(symbolsArrayList.contains(theLine.charAt(endIndex))){
                                isPartNumber = true;
                                lineNum = lineIndex;
                            }
                            if(symbolsArrayList.contains('*')){
                                isGear = true;
                            }
                        }

                    }

                    if(!isPartNumber){
                        // check line above
                        if(startIndex == 0){
                            for(int k = 0; k <= endIndex; k++){
                                if(aboveLine.charAt(k)!='.'){
                                    if(symbolsArrayList.contains(aboveLine.charAt(k))){
                                        isPartNumber = true;
                                        lineNum = lineIndex;
                                        if(symbolsArrayList.contains('*')){
                                            isGear = true;
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                        else if(startIndex > 0){

                            if(endIndex < theLine.length()){
                                for(int k = startIndex - 1; k <= endIndex; k++){
                                    if(aboveLine.charAt(k)!='.'){
                                        if(symbolsArrayList.contains(aboveLine.charAt(k))){
                                            isPartNumber = true;
                                            lineNum = lineIndex;
                                            if(symbolsArrayList.contains('*')){
                                                isGear = true;
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                            else{
                                for(int k = startIndex - 1; k <= endIndex-1; k++){
                                    if(aboveLine.charAt(k)!='.'){
                                        if(symbolsArrayList.contains(aboveLine.charAt(k))){
                                            isPartNumber = true;
                                            lineNum = lineIndex;
                                            if(symbolsArrayList.contains('*')){
                                                isGear = true;
                                            }
                                            break;
                                        }
                                    }
                                }
                            }

                        }
                    }


                    if(!isPartNumber){
                        // check line below
                        if(startIndex == 0){
                            for(int k = 0; k <= endIndex; k++){
                                if(belowLine.charAt(k)!='.'){
                                    if(symbolsArrayList.contains(belowLine.charAt(k))){
                                        isPartNumber = true;
                                        lineNum = lineIndex;
                                        if(symbolsArrayList.contains('*')){
                                            isGear = true;
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                        else if(startIndex > 0){

                            if(endIndex < theLine.length()){
                                for(int k = startIndex - 1; k <= endIndex; k++){
                                    if(belowLine.charAt(k)!='.'){
                                        if(symbolsArrayList.contains(belowLine.charAt(k))){
                                            isPartNumber = true;
                                            lineNum = lineIndex;
                                            if(symbolsArrayList.contains('*')){
                                                isGear = true;
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                            else {
                                for(int k = startIndex - 1; k <= endIndex-1; k++){
                                    if(belowLine.charAt(k)!='.'){
                                        if(symbolsArrayList.contains(belowLine.charAt(k))){
                                            isPartNumber = true;
                                            lineNum = lineIndex;
                                            if(symbolsArrayList.contains('*')){
                                                isGear = true;
                                            }
                                            break;
                                        }
                                    }
                                }
                            }

                        }
                    }


//                    System.out.println("isPartNumber = "+isPartNumber);
                    if(isPartNumber){
//                        sum += theNumber;
                        int[] quadruple = new int[4];
                        quadruple[0]=startIndex;
                        quadruple[1]=endIndex;
                        quadruple[2]=theNumber;
                        quadruple[3]=lineNum;

                        partNumbersArrayList.add(quadruple);
                        isPartNumber = false;
                        isGear = false;

                    }


                }
            }
        }


//        System.out.println("partNumber start index | partNumber start index | the partNumber | partNumber lineNum");
//        for(int item = 0; item< partNumbersArrayList.size(); item++){
//            System.out.print("["+partNumbersArrayList.get(item)[0]+","+partNumbersArrayList.get(item)[1]+","+
//                    partNumbersArrayList.get(item)[2]+","+partNumbersArrayList.get(item)[3]+"]\n");
//        }
        return findGearRatioSum(partNumbersArrayList, allLines);
    }
}
