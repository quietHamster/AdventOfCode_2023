import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CalculateSumOfLastValues {



    public static void main(String[] args) throws IOException {

        String fileName = "src/main/java/input.txt";

        System.out.println(findSum(fileName));
    }

    private static ArrayList<long[]> data = new ArrayList<>();

    private static long findSum(String fileName) throws IOException {
        long sum = 0;

        getData(fileName);

        /**** part 1 ***/
        /*
        for(int index = 0; index < data.size(); index++){
            long[] sequence = data.get(index);
            long lastValue = findLastValueOfSequence(sequence);
            sum+=lastValue;
        }
        */

        /**** part 2 ***/
        for(int index = 0; index < data.size(); index++){
            long[] sequence = data.get(index);
            long firstValue = findFirstValueOfSequence(sequence);
            sum+=firstValue;
        }

        return sum;
    }

    private static long findFirstValueOfSequence(long[] sequence){
        long value = 0;

        ArrayList<Long> running_array = new ArrayList<>();
        ArrayList<Long> allFirstValues = new ArrayList<>();
        allFirstValues.add(sequence[0]);

        for(int index = 0; index < sequence.length - 1; index++){
            long diff = sequence[index+1] - sequence[index];
            running_array.add(diff);
        }

        allFirstValues.add(running_array.get(0));
        
        boolean keepGoing = true;

        while(keepGoing){

            long[] temp_array = new long[running_array.size()-1];

            for (int index_ra = 0; index_ra < running_array.size()-1; index_ra++){
                long diff = running_array.get(index_ra+1) - running_array.get(index_ra);
                temp_array[index_ra] = diff;
            }

            
            running_array.clear();

            boolean hasZero = false;

            for(int index_ta = 0; index_ta< temp_array.length;index_ta++){
                running_array.add(temp_array[index_ta]);
                if(temp_array[index_ta] == 0){
                    hasZero = true;
                }
            }

            allFirstValues.add(running_array.get(0));
            
            if(hasZero){
                keepGoing = false;

                for(int index_ra = 0; index_ra < running_array.size(); index_ra++){
                    if(index_ra != running_array.size()-1){
                        if(running_array.get(index_ra) != running_array.get(index_ra+1)){
                            keepGoing = true;
                            break;
                        }
                    }
                    else{
                        if(running_array.get(index_ra-1) != running_array.get(index_ra)){
                            keepGoing = true;
                            break;
                        }
                    }

                }
            }
        }

        long temp = 0;
        for(int index = allFirstValues.size() - 1; index > 0; index--){
            if(index == allFirstValues.size() - 1){
                temp = allFirstValues.get(index-1) - allFirstValues.get(index);
            }
            else{
                temp = allFirstValues.get(index - 1) - temp;
            }
        }

        value = temp;
        return value;
    }

    private static long findLastValueOfSequence(long[] sequence){
        long value = 0;

        value += sequence[sequence.length-1];

        ArrayList<Long> running_array = new ArrayList<>();

        for(int index = sequence.length - 1; index >= 1; index--){
            long diff = sequence[index] - sequence[index-1];
            running_array.add(diff);
        }

        boolean keepGoing = true;
        while(keepGoing){

            value += running_array.get(0);

            long[] temp_array = new long[running_array.size()-1];

            for (int index_ra = 0; index_ra < running_array.size()-1; index_ra++){
                long diff = running_array.get(index_ra) - running_array.get(index_ra+1);
                temp_array[index_ra] = diff;
            }

            running_array.clear();

            boolean hasZero = false;

            for(int index_ta = 0; index_ta< temp_array.length;index_ta++){
                running_array.add(temp_array[index_ta]);
                if(temp_array[index_ta] == 0){
                    hasZero = true;
                }
            }


            if(hasZero){
                keepGoing = false;

                for(int index_ra = 0; index_ra < running_array.size(); index_ra++){
                    if(index_ra != running_array.size()-1){
                        if(running_array.get(index_ra) != running_array.get(index_ra+1)){
                            keepGoing = true;
                            break;
                        }
                    }
                    else{
                        if(running_array.get(index_ra-1) != running_array.get(index_ra)){
                            keepGoing = true;
                            break;
                        }
                    }

                }
            }

        }

        return value;
    }

    private static void getData(String fileName) throws IOException {
        String line = null;

        BufferedReader reader;

        try{
            reader = new BufferedReader(new FileReader(fileName));

            line = reader.readLine();

            while(line != null){
                if(line.length()!=0){
                    String[] temptString_Array = line.split(" ");
                    long[] temLong_Array = new long[temptString_Array.length];

                    for (int index = 0; index < temptString_Array.length; index++){
                        temLong_Array[index] = Long.parseLong(temptString_Array[index]);

                    }

                    data.add(temLong_Array);
                }


                line = reader.readLine();
            }
        }
        catch(FileNotFoundException e){
            throw new RuntimeException(e);
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }

        reader.close();
    }

}
