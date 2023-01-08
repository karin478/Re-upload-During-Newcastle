import java.util.ArrayList;
import java.util.function.IntFunction;
import java.util.stream.Stream;


/*This SortedArraylist class is used to reorder the Arrylist of the Event class
and the Client class using the insertion method according to the rules written
by the override comparTo in the Event and Client classes.*/


public class SortedArrayList<E extends Comparable> extends ArrayList<E> {


/*This method uses generics to insert sorting from the selected values of Event
and Client, select an element to compare with compareTo, and the return value
will be -1 or 1. The new one can be determined by the return value of compareTO
The position of element E in this sorted array*/
    public void insert(E e) {
        this.add(e);
        int lastIndex = 0;
        for( lastIndex = this.size() -1 ; lastIndex > 0 && this.get(lastIndex-1).compareTo(e) > 0 ; lastIndex--){
            this.set(lastIndex, this.get(lastIndex-1));
        }
        this.set(lastIndex,e);
    }


    public SortedArrayList() {
    }


    @Override
    public <T> T[] toArray(IntFunction<T[]> generator) {
        return super.toArray(generator);
    }

    @Override
    public Stream<E> stream() {
        return super.stream();
    }

    @Override
    public Stream<E> parallelStream() {
        return super.parallelStream();
    }

}