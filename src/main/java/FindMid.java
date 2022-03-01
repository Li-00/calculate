import com.csvreader.CsvReader;
import org.junit.Test;

import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;

public class FindMid {



    public static void main(String[] args) {
        findMid("D:\\desk\\Calculate\\Test.csv");
    }

    //根据小顶堆找中位数
    public static void findMid(String filePath) {
        try {
            CsvReader csvReader = new CsvReader(filePath);
            while (csvReader.readRecord()) {
                Integer a = Integer.parseInt(csvReader.get(2));
                Integer b = Integer.parseInt(csvReader.get(3));

                Insert(a - b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(GetMedian());
    }



    static PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();//小顶堆
    //大顶堆
    static PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(20,new Comparator<Integer>(){

        @Override
        public int compare(Integer o1, Integer o2){
            return o2-o1;
        }
    });

    private static int count = 0;//计数据流中已入堆数
    /**
     * 新数插入
     */
    public static void Insert(Integer num) {
        count++;
        //当数据的总数是偶数时，且比最大堆堆顶小，则将新数字插入最大堆
        //然后把最大堆中最大的数字拿出，插入到最小堆
        //否则直接将新数放入最小堆
        if(count%2==0){
            if(!maxHeap.isEmpty() && num<maxHeap.peek()){
                maxHeap.add(num);
                num = maxHeap.poll();
            }
            minHeap.add(num);
        }
        //当数据的总数是奇数时，且比最小堆顶还大，则将新数字插入最小堆
        //然后把最小堆中最小的数字拿出，插入到最大堆
        //否则直接将新数放入最大堆
        else{
            if(!minHeap.isEmpty() && num>minHeap.peek()){
                minHeap.add(num);
                num = minHeap.poll();
            }
            maxHeap.add(num);
        }
    }

    /**
     * 获取中位数
     */
    public static Double GetMedian() {
        //若数字总数是偶数，则大顶堆堆顶和小顶堆堆顶的平均数即为中位数
        if(maxHeap.size()==minHeap.size()){
            return (maxHeap.peek()+minHeap.peek())/2.0;
        }
        else if(maxHeap.size()>minHeap.size()){
            return maxHeap.peek()*1.0;
        }
        else{
            return minHeap.peek()*1.0;
        }
    }

}
