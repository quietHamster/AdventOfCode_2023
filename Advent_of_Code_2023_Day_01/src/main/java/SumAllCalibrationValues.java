import java.io.*;
import java.util.*;

import static java.lang.Character.isDigit;

public class SumAllCalibrationValues {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "src/main/java/input.txt";

        System.out.println(findSum(fileName));

    }

    private static ArrayList<String> wordsArray=new ArrayList<>();

    private static String replaceWordByNumber(String aString){
        wordsArray.add("one");
        wordsArray.add("two");
        wordsArray.add("three");
        wordsArray.add("four");
        wordsArray.add("five");
        wordsArray.add("six");
        wordsArray.add("seven");
        wordsArray.add("eight");
        wordsArray.add("nine");

        String result = aString;

        ArrayList<Integer> wdLocation = new ArrayList<>();
        ArrayList<String> wdItems = new ArrayList<>();
        HashMap<Integer,String> hashMap = new HashMap<>();

        if(aString.indexOf("one")>-1){
            int tempIndex = aString.indexOf("one");
            int oneLowIndex = -1;
            int oneHighIndex = -1;
            while(tempIndex>-1){
                if(oneLowIndex == oneHighIndex){
                    oneLowIndex = tempIndex;
                }

                tempIndex = aString.indexOf("one",tempIndex+1);
                if(tempIndex>oneLowIndex){
                    oneHighIndex = tempIndex;
                }

            }

            hashMap.put(oneLowIndex,"one_low");
            hashMap.put(oneHighIndex,"one_high");
        }

        if(aString.indexOf("two")>-1){
            int tempIndex = aString.indexOf("two");
            int twoLowIndex = -1;
            int twoHighIndex = -1;
            while(tempIndex>-1){
                if(twoLowIndex == twoHighIndex){
                    twoLowIndex = tempIndex;
                }

                tempIndex = aString.indexOf("two",tempIndex+1);
                if(tempIndex>twoLowIndex){
                    twoHighIndex = tempIndex;
                }

            }

            hashMap.put(twoLowIndex,"two_low");
            hashMap.put(twoHighIndex,"two_high");
        }

        if(aString.indexOf("three")>-1){
            int tempIndex = aString.indexOf("three");
            int threeLowIndex = -1;
            int threeHighIndex = -1;
            while(tempIndex>-1){
                if(threeLowIndex == threeHighIndex){
                    threeLowIndex = tempIndex;
                }

                tempIndex = aString.indexOf("three",tempIndex+1);
                if(tempIndex>threeLowIndex){
                    threeHighIndex = tempIndex;
                }

            }

            hashMap.put(threeLowIndex,"three_low");
            hashMap.put(threeHighIndex,"three_high");
        }

        if(aString.indexOf("four")>-1){
            int tempIndex = aString.indexOf("four");
            int fourLowIndex = -1;
            int fourHighIndex = -1;
            while(tempIndex>-1){
                if(fourLowIndex == fourHighIndex){
                    fourLowIndex = tempIndex;
                }

                tempIndex = aString.indexOf("four",tempIndex+1);
                if(tempIndex>fourLowIndex){
                    fourHighIndex = tempIndex;
                }

            }

            hashMap.put(fourLowIndex,"four_low");
            hashMap.put(fourHighIndex,"four_high");
        }

        if(aString.indexOf("five")>-1){
            int tempIndex = aString.indexOf("five");
            int fireLowIndex = -1;
            int fireHighIndex = -1;
            while(tempIndex>-1){
                if(fireLowIndex == fireHighIndex){
                    fireLowIndex = tempIndex;
                }

                tempIndex = aString.indexOf("five",tempIndex+1);
                if(tempIndex>fireLowIndex){
                    fireHighIndex = tempIndex;
                }

            }

            hashMap.put(fireLowIndex,"five_low");
            hashMap.put(fireHighIndex,"five_high");
        }

        if(aString.indexOf("six")>-1){
            int tempIndex = aString.indexOf("six");
            int sixLowIndex = -1;
            int sixHighIndex = -1;
            while(tempIndex>-1){
                if(sixLowIndex == sixHighIndex){
                    sixLowIndex = tempIndex;
                }

                tempIndex = aString.indexOf("six",tempIndex+1);
                if(tempIndex>sixLowIndex){
                    sixHighIndex = tempIndex;
                }

            }

            hashMap.put(sixLowIndex,"six_low");
            hashMap.put(sixHighIndex,"six_high");
        }

        if(aString.indexOf("seven")>-1){
            int tempIndex = aString.indexOf("seven");
            int sevenLowIndex = -1;
            int sevenHighIndex = -1;
            while(tempIndex>-1){
                if(sevenLowIndex == sevenHighIndex){
                    sevenLowIndex = tempIndex;
                }

                tempIndex = aString.indexOf("seven",tempIndex+1);
                if(tempIndex>sevenLowIndex){
                    sevenHighIndex = tempIndex;
                }

            }

            hashMap.put(sevenLowIndex,"seven_low");
            hashMap.put(sevenHighIndex,"seven_high");
        }

        if(aString.indexOf("eight")>-1){
            int tempIndex = aString.indexOf("eight");
            int eightLowIndex = -1;
            int eightHighIndex = -1;
            while(tempIndex>-1){
                if(eightLowIndex == eightHighIndex){
                    eightLowIndex = tempIndex;
                }

                tempIndex = aString.indexOf("eight",tempIndex+1);
                if(tempIndex>eightLowIndex){
                    eightHighIndex = tempIndex;
                }

            }

            hashMap.put(eightLowIndex,"eight_low");
            hashMap.put(eightHighIndex,"eight_high");
        }

        if(aString.indexOf("nine")>-1){
            int tempIndex = aString.indexOf("nine");
            int nineLowIndex = -1;
            int nineHighIndex = -1;
            while(tempIndex>-1){
                if(nineLowIndex == nineHighIndex){
                    nineLowIndex = tempIndex;
                }

                tempIndex = aString.indexOf("nine",tempIndex+1);
                if(tempIndex>nineLowIndex){
                    nineHighIndex = tempIndex;
                }

            }

            hashMap.put(nineLowIndex,"nine_low");
            hashMap.put(nineHighIndex,"nine_high");
        }

        if(hashMap.size()>0){
            Integer keyArray[]= hashMap.keySet().toArray(new Integer[0]);
            ArrayList<Integer> noneNegativeIndexArray = new ArrayList<>();
            for (int key = 0; key < keyArray.length; key++){
                if(keyArray[key]>-1){
                    noneNegativeIndexArray.add(keyArray[key]);
                }
            }
            Collections.sort(noneNegativeIndexArray);

            int smallIndex = noneNegativeIndexArray.get(0);
            int largeIndex = noneNegativeIndexArray.get(noneNegativeIndexArray.size()-1);

            String wordDigitLow = hashMap.get(smallIndex);
            int dashIndexL = wordDigitLow.indexOf("_");
            wordDigitLow = wordDigitLow.substring(0,dashIndexL);
            String wordDigitHigh = hashMap.get(largeIndex);
            int dashIndexH = wordDigitHigh.indexOf("_");
            wordDigitHigh = wordDigitHigh.substring(0,dashIndexH);

            boolean focusLeft = true;
            boolean focusRight = true;

            if(largeIndex-smallIndex==2 || largeIndex-smallIndex==3 || largeIndex-smallIndex==4){
                for (int loca1 = 0; loca1 < smallIndex; loca1++){
                    if(isDigit(aString.charAt(loca1))){
                        focusLeft = false;
                        break;
                    }
                }

                for (int loca2 = largeIndex+3; loca2 < aString.length();loca2++ ){
                    if(isDigit(aString.charAt(loca2))){
                        focusRight = false;
                        break;
                    }
                }

                if (focusLeft){
                    result = result.replace(wordDigitLow,Integer.toString(wordsArray.indexOf(wordDigitLow)+1));
                }
                if (focusRight){
                    result = result.replace(wordDigitHigh,Integer.toString(wordsArray.indexOf(wordDigitHigh)+1));
                }
            }

            else{
                String tempString0 = "";
                if (focusLeft){
                    String tempString1 = aString.substring(smallIndex+wordDigitLow.length());
                    String tempString2 = aString.substring(0,smallIndex+wordDigitLow.length());
                    tempString2 = tempString2.replace(wordDigitLow,Integer.toString(wordsArray.indexOf(wordDigitLow)+1));
                    tempString0 = tempString2.concat(tempString1);

//                    result = result.replace(wordDigitLow,Integer.toString(wordsArray.indexOf(wordDigitLow)+1));
                }
                if (focusRight){
                    if(smallIndex<largeIndex){
                        String tempString3 = tempString0.substring(largeIndex-wordDigitLow.length());
                        String tempString4 = tempString0.substring(0,largeIndex-wordDigitLow.length());
                        tempString3 = tempString3.replace(wordDigitHigh,Integer.toString(wordsArray.indexOf(wordDigitHigh)+1));
                        tempString0 = tempString4.concat(tempString3);
                    }


//                    result = result.replace(wordDigitHigh,Integer.toString(wordsArray.indexOf(wordDigitHigh)+1));
                }

                result = tempString0;
//                result = result.replace(wordDigitLow,Integer.toString(wordsArray.indexOf(wordDigitLow)+1));
//                result = result.replace(wordDigitHigh,Integer.toString(wordsArray.indexOf(wordDigitHigh)+1));
            }

        }

       return result;

    }

    private static long findSum(String fileName) throws FileNotFoundException {
        long sum = 0;
        String line = null;
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(fileName));
            line = reader.readLine();

            while (line != null) {

                System.out.println(line);

                String aLine = replaceWordByNumber(line.toString());

                int digitCounter = 0;
                int lineValue = 0;
                int digit1Value = 0;
                int digit2Value = 0;

                for (int location = 0; location < aLine.length(); location++) {
                    if (isDigit(aLine.charAt(location))) {
                        digitCounter++;
                    }
                }

                if (digitCounter == 1){
                    for (int location = 0; location < aLine.length(); location++) {
                        if(isDigit(aLine.charAt(location))){
                            digit1Value = Integer.parseInt(Character.toString(aLine.charAt(location)))*10;
                            digit2Value = Integer.parseInt(Character.toString(aLine.charAt(location)));
                        }
                    }

                }

                if(digitCounter == 2){
                    int localCounter = 0;
                    for (int location = 0; location < aLine.length(); location++) {
                        if(isDigit(aLine.charAt(location))){
                            localCounter++;
                            if(localCounter == 1){
                                digit1Value = Integer.parseInt(Character.toString(aLine.charAt(location)))*10;
                            }

                            if(localCounter == 2){
                                digit2Value = Integer.parseInt(Character.toString(aLine.charAt(location)));
                            }
                        }

                    }
                }

                if (digitCounter > 2){
                    int localCounter2 = 0;
                    int lastDigitLocation = 0;
                    for (int location = 0; location < aLine.length(); location++) {
                        if(isDigit(aLine.charAt(location))){
                            localCounter2++;
                            if(localCounter2 == 1){
                                digit1Value = Integer.parseInt(Character.toString(aLine.charAt(location)))*10;
                            }

                            lastDigitLocation = location;



                        }

                        if(location == aLine.length()-1){
                            digit2Value = Integer.parseInt(Character.toString(aLine.charAt(lastDigitLocation)));
                        }

                    }
                }

                lineValue = digit1Value+digit2Value;
                System.out.println(lineValue);
                sum += lineValue;
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        
//        System.out.println(line);

        return sum;
    }
}
