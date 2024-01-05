import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CalculateMarginError {

    public static void main(String[] args){

        long startTime = System.nanoTime();

        String fileName = "src/main/java/input.txt";
        System.out.println(calculatingMarginError(fileName));

        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("totalTime = "+totalTime);

    }

    private static long calculatingMarginError(String fileName){

//        ArrayList<long[]> inputData = readData_part1(fileName);
        ArrayList<long[]> inputData = readData_part2(fileName);

        long marginError = 1;

        for(int index = 0; index<inputData.size(); index++){
            long time = inputData.get(index)[0];
            long record = inputData.get(index)[1];

            long counter = 0;

            for(long timeIndex = 0; timeIndex <= time/2; timeIndex++ ){
                long distance = timeIndex * (time - timeIndex);
                if(distance > record)
                    counter++;
            }

            counter *= 2;

            if(time % 2 == 0){ // it is actually "odd" because of 0-base
                counter--;
            }

            marginError *= counter;
        }

        return marginError;
    }

    private static ArrayList<long[]> readData_part1(String fileName){

        String line = null;
        String[] time_string = null;
        String[] distance_string = null;
        int counter = 0;

        ArrayList<long[]> data = new ArrayList<>();

        BufferedReader reader;

        try{
            reader = new BufferedReader(new FileReader(fileName));

            line = reader.readLine();

            while(line != null){
                int cutIndex = -1;

                for(int charIndex = 0; charIndex < line.length(); charIndex++){
                    if(Character.isDigit(line.charAt(charIndex))){
                        cutIndex = charIndex;
                        break;
                    }
                }
                String tempStr = line.substring(cutIndex).trim();

                if(counter == 0){
                    tempStr = tempStr.replaceAll("\\\\s{2,}"," ").trim();
                    time_string = tempStr.split(" ");
                }
                else if(counter == 1){
                    tempStr = tempStr.replaceAll("\\\\s{2,}"," ").trim();
                    distance_string = tempStr.split(" ");
                }
                counter++;
                line = reader.readLine();
            }

            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ArrayList<Long> time_arrayList = new ArrayList<>();
        for(int index = 0; index < time_string.length; index++){
            if(!"".equals(time_string[index])){
                time_arrayList.add(Long.parseLong(time_string[index]));
            }
        }

        ArrayList<Long> distance_arrayList = new ArrayList<>();
        for(int index = 0; index < distance_string.length; index++){
            if(!"".equals(distance_string[index])){
                distance_arrayList.add(Long.parseLong(distance_string[index]));
            }
        }

        for(int index = 0; index < time_arrayList.size(); index++){
            long time = time_arrayList.get(index);
            long distance = distance_arrayList.get(index);
            long[] pair = new long[2];
            pair[0] = time;
            pair[1] = distance;
            data.add(pair);
        }

        return data;
    }

    private static ArrayList<long[]> readData_part2(String fileName){

        String line = null;
        String time_string = "";
        String distance_string = "";
        int counter = 0;

        ArrayList<long[]> data = new ArrayList<>();

        BufferedReader reader;

        try{
            reader = new BufferedReader(new FileReader(fileName));

            line = reader.readLine();

            while(line != null){
                int cutIndex = -1;

                for(int charIndex = 0; charIndex < line.length(); charIndex++){
                    if(Character.isDigit(line.charAt(charIndex))){
                        cutIndex = charIndex;
                        break;
                    }
                }
                String tempStr = line.substring(cutIndex).trim();

                for(int index = 0; index < tempStr.length(); index++){
                    if(Character.isDigit(tempStr.charAt(index))){
                        if(counter == 0){
                            time_string = time_string.concat(Character.toString(tempStr.charAt(index)));
                        }
                        else if(counter == 1){
                            distance_string = distance_string.concat(Character.toString(tempStr.charAt(index)));
                        }
                    }


                }

                counter++;
                line = reader.readLine();
            }

            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        long time = Long.parseLong(time_string);
        long distance = Long.parseLong(distance_string);
        long[] pair = new long[2];
        pair[0] = time;
        pair[1] = distance;
        data.add(pair);

        return data;
    }

}
