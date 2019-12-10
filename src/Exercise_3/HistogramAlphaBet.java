package Exercise_3;

import java.util.Map;
import java.util.Iterator;
import java.util.LinkedHashMap;
import static java.util.stream.Collectors.toMap;




public class HistogramAlphaBet {
    private int n; // used to determine n probabilty of characters.
    public Map<Character,Double> mysort; // used temporily to sort
    private LinkedHashMap<Character,Double> myprop = new LinkedHashMap<>(); // returns the probability as the values and the letters as the keys.

    public HistogramAlphaBet(int n, Map<Character, Double> temphash)
    {
        this.n = n;


        Map<Character, Double> mysort = temphash
                .entrySet()
                .stream()
                .collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));

        this.mysort = mysort;

    }

    // This will calculate the probabilty
    public LinkedHashMap<Character,Double> calcP(){
        double sum = 0; // Hold sum of all letters

        // Calculates the the sum of values or the letter counts
        for(double vals: mysort.values()){
            sum = sum + vals;
        }
        int count = 0;

        Iterator<Map.Entry<Character, Double>> itr = mysort.entrySet().iterator();

        while(itr.hasNext())
        {
            if(count == n){
                break;
            }

            Map.Entry<Character, Double> entry = itr.next();
            myprop.put(entry.getKey(),entry.getValue()/sum);
            count =count + 1;
        }


        return myprop;
    }

}
