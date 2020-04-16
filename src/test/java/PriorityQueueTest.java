import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PriorityQueueTest {

    // parameterized test

    private Integer[] expected_data;
    private Integer[] random_data;

    public PriorityQueueTest(Integer[] expectedResult, Integer[] randomData){
        this.expected_data = expectedResult;
        this.random_data = randomData;
    }

    @Parameterized.Parameters
    public static Collection getTestParameters(){
        return Arrays.asList(new Integer[][][]{
                {{1, 2, 3}, {3, 1, 2}},
                {{22, 44, 55, 66}, {22, 55, 44, 66}},
                {{123, 456, 789}, {123, 789, 456}},
                {{0, 3, 4, 8, 9}, {9, 0, 4, 3, 8}},
                {{0, 1, 2, 3, 4, 5}, {1, 2, 3, 4, 5, 0}}
            });
    }

    @Test
    public void testGetCorrectOrder(){
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for(int i=0; i<random_data.length; i++){
            pq.offer(random_data[i]);
        }
        int j = 0;
        Integer tmp = 0;
        while((tmp = pq.poll()) != null){
            assertEquals(expected_data[j], tmp);
            j++;
        }
    }

    // exception test
    // ref. https://www.geeksforgeeks.org/java-util-priorityqueue-class-java/

    @Test(expected = NullPointerException.class)
    public void whenOfferExceptionThrown(){
        PriorityQueue<String> pq = new PriorityQueue<String>();
        pq.offer(null);
    }

    @Test(expected = NullPointerException.class)
    public void whenAddNullExceptionThrown(){
        PriorityQueue<String> pq = new PriorityQueue<String>();
        pq.add(null);
    }

    @Test(expected = ClassCastException.class)
    public void whenAddClassExceptionThrown(){
        class User{
            public final String name;
            public final String ID;

            public User(String name, String ID){
                this.name = name;
                this.ID = ID;
            }
            public String toString(){
                return name + "/" + ID;
            }
        }
        PriorityQueue<User> pq = new PriorityQueue<User>();
        pq.offer(new User("Bob", "1234"));
    }


}
