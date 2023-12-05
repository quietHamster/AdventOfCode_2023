import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.Character.isDigit;

public class SumIdsOfPossibleGames {
    public static void main(String[] args) {
        String fileName = "src/main/java/input.txt";

        System.out.println(findSum(fileName));

    }

    private static final long redLimit = 12;
    private static final long greenLimit = 13;
    private static final long blueLimit = 14;

    private static long[] processOneSet(String aLine){
        //[R,G,B]
        long[] result = new long[3];
        int redIndex = aLine.indexOf("red");
        int greenIndex = aLine.indexOf("green");
        int blueIndex = aLine.indexOf("blue");

        if(-1==redIndex){
            result[0]=0;
        }
        if(-1==greenIndex){
            result[1]=0;
        }
        if(-1==blueIndex){
            result[2]=0;
        }

        if(-1<redIndex){
            if(3<redIndex){
                if(' '==aLine.charAt(redIndex-4)){ // between 10 and 99
                    result[0] = Integer.parseInt(aLine.substring(redIndex-3,redIndex-1));
                }
                if(' '==aLine.charAt(redIndex-3)){ // between 1 and 9
                    result[0] = Integer.parseInt(aLine.substring(redIndex-2,redIndex-1));
                }
            }
            else{
                if(' '==aLine.charAt(redIndex-3)){ // between 1 and 9
                    result[0] = Integer.parseInt(aLine.substring(redIndex-2,redIndex-1));
                }
            }
        }

        if(-1<greenIndex){
            if(3<greenIndex){
                if(' '==aLine.charAt(greenIndex-4)){ // between 10 and 99
                    result[1] = Integer.parseInt(aLine.substring(greenIndex-3,greenIndex-1));
                }
                if(' '==aLine.charAt(greenIndex-3)){ // between 1 and 9
                    result[1] = Integer.parseInt(aLine.substring(greenIndex-2,greenIndex-1));
                }
            }
            else{
                if(' '==aLine.charAt(greenIndex-3)){ // between 1 and 9
                    result[1] = Integer.parseInt(aLine.substring(greenIndex-2,greenIndex-1));
                }
            }
        }

        if(-1<blueIndex){
            if(3<blueIndex){
                if(' '==aLine.charAt(blueIndex-4)){ // between 10 and 99
                    result[2] = Integer.parseInt(aLine.substring(blueIndex-3,blueIndex-1));
                }
                if(' '==aLine.charAt(blueIndex-3)){ // between 1 and 9
                    result[2] = Integer.parseInt(aLine.substring(blueIndex-2,blueIndex-1));
                }
            }
            else{
                if(' '==aLine.charAt(blueIndex-3)){ // between 1 and 9
                    result[2] = Integer.parseInt(aLine.substring(blueIndex-2,blueIndex-1));
                }
            }
        }

        return result;
    }

    private static void printTriple(long[] aTriple){
        for (int num = 0; num < aTriple.length;num++){
            System.out.print(aTriple[num]+" ");
        }
        System.out.println();
    }

    public static long findSum(String fileName){
        long sum = 0;
        long setPower = 0;
        String line = null;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            line = reader.readLine();
            long red = 0;
            long green = 0;
            long blue = 0;
            long maxRed = 0;
            long maxGreen = 0;
            long maxBlue = 0;

            // [R,G,B]
            long[] triple = new long[3];
            boolean isValidGame = true;

            while (line != null) {

                System.out.println(line);

                int colonIndex = line.indexOf(":");

                int gameNum = Integer.parseInt(line.substring(5,colonIndex));
                line = line.substring(colonIndex+1);

                int numSemicolons = 0;

                for (int charIndex = 0; charIndex < line.length(); charIndex++){
                    if(';'==line.charAt(charIndex)){
                        numSemicolons++;
                    }
                }



                if(numSemicolons==0){
                    triple = processOneSet(line);
//                    printTriple(triple);
                    red = triple[0];
                    green = triple[1];
                    blue = triple[2];
//                    if(red>redLimit || blue > blueLimit|| green > greenLimit){
//                        isValidGame = false;
//                    }
                    System.out.println("maxRed = "+maxRed);
                    System.out.println("maxGreen = "+maxGreen);
                    System.out.println("maxBlue = "+maxBlue);
                    setPower = maxRed*maxGreen*maxBlue;
                    System.out.println("setPower = "+setPower);
                }
                else{
                    for (int semicolon = 0; semicolon <= numSemicolons; semicolon++){
                        int semiColonIndex = line.indexOf(";");
                        if(-1<semiColonIndex){
                            triple = processOneSet(line.substring(0,line.indexOf(";")));
                        }
                        else{
                            triple = processOneSet(line);
                        }

//                        printTriple(triple);
                        red = triple[0];
                        green = triple[1];
                        blue = triple[2];
//                        if(red>redLimit || blue > blueLimit|| green > greenLimit){
//                            isValidGame = false;
//                        }

                        if(red>maxRed){
                            maxRed = red;
                        }
                        if(green > maxGreen){
                            maxGreen = green;
                        }
                        if(blue > maxBlue){
                            maxBlue = blue;
                        }


                        line = line.substring(semiColonIndex+1);
                        triple[0]=0;
                        triple[1]=0;
                        triple[2]=0;

                    }
                    System.out.println("maxRed = "+maxRed);
                    System.out.println("maxGreen = "+maxGreen);
                    System.out.println("maxBlue = "+maxBlue);
                    setPower = maxRed*maxGreen*maxBlue;
                    System.out.println("setPower = "+setPower);
                }



                /*
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


                */

//                if(isValidGame){
//                    sum += gameNum;
//                }

                sum += setPower;


                line = reader.readLine();
                red = 0;
                green = 0;
                blue = 0;
//                isValidGame = true;
                maxRed = 0;
                maxGreen = 0;
                maxBlue = 0;
                setPower = 0;
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sum;
    }
}
