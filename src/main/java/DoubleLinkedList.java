import java.rmi.server.ExportException;
import java.sql.Array;
import java.util.*;

public class DoubleLinkedList<T extends Comparable<T>> implements Collection<String> {

    public LinkedList<LinkedList<String>> data;
    public String[][] resultOfToArray;
    public Integer linkListCapacity;


    public DoubleLinkedList(Integer linkListCapacity){
        this.data = new LinkedList<>();
        data.add(new LinkedList<String>());
        this.linkListCapacity = linkListCapacity;

    }


    private class DoubleIterator implements Iterator<String> {
        final Iterator<LinkedList<String>> outIterator;
        Iterator<String> inIterator;


        public DoubleIterator() {
            if(data.getLast()==null){data.removeLast();}
            outIterator = data.iterator();
            inIterator = outIterator.next().iterator();
        }

        @Override
        public boolean hasNext() {


            return outIterator.hasNext() || inIterator.hasNext();
        }

        @Override
        public String next() {

            if (inIterator.hasNext()) {
                return inIterator.next();
            }
            if (outIterator.hasNext()) {
                LinkedList<String> lL = outIterator.next();
                inIterator = lL.iterator();
                return inIterator.next();
            }
            return null;
        }
    }


    public DoubleIterator getIterator() {
        if(data.getLast().size()==0){data.removeLast();}
        return new DoubleIterator();
    }


    @Override
    public int size() {
        Integer count = 0;
        for (LinkedList<String> linkedList : data) {
           count = count + linkedList.size();
        }
        return count;
    }


    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {

        for (LinkedList<String> llS : data) {
            if (llS.contains(o))
                return true;
        }
        return false;
    }


    @Override
    public Iterator<String> iterator() {
        if(data.getLast()==null){data.removeLast();}
        return  getIterator();
    }


    @Override
    public boolean add(String string) {
        if (string==null){
            throw new RuntimeException ("The string can't be a null");
        }
        this.data.getLast().add(string);


        if ((data.getLast().size() == linkListCapacity)) {
            data.add(new LinkedList<>());
        }return true;

    }

    @Override
    public String[][] toArray() {

        if(data.getLast().size()==0){data.removeLast();}


        String[][] arr = new String[data.size()][];
        for (int k = 0; k < data.size(); k++) {
            LinkedList<String> ll = data.get(k);
            String[] strArr = new String[ll.size()];

            for (int j = 0; j < strArr.length; j++) {
                strArr[j] = ll.get(j);
            }
            arr[k]=strArr;
        }
        resultOfToArray=arr;

        return resultOfToArray;
    }

    @Override//??
    public <String> String[] toArray(String[] a) {
        java.lang.String[][] b= Arrays.copyOf(resultOfToArray, resultOfToArray.length);

        return (String[]) b;
    }
    @Override
    public boolean remove(Object o) {

        for (LinkedList<String> llS : data) {
            llS.remove(o);}
            data.removeIf(llS -> llS.size() == 0);

        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {

        LinkedList<String> tempLl = new LinkedList<>();
        for (LinkedList<String> llS : data) {
            tempLl.addAll(llS);
            if (tempLl.containsAll(c)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends String> c) throws IllegalArgumentException{

        if(data.getLast().size()==0){data.removeLast();}

        for (String str : c) {
            data.getLast().add(str);


            if (data.getLast().size() > linkListCapacity) {
                data.add(new LinkedList<>());
            }
        }return true;
    }
    @Override
    public boolean removeAll(Collection<?> c) {
        for(LinkedList<String> llS:data){
            llS.removeAll(c);
          }
        data.removeIf(llS -> llS.size() == 0);
            return true;
    }


    @Override
    public boolean retainAll(Collection<?> c) {
        for(LinkedList<String> llS:data){
            llS.retainAll(c);
        }
        data.removeIf(llS -> llS.size() == 0);
        return true;


    }

    @Override
    public void clear() {
        data.clear();
      }

    @Override
    public boolean equals(Object object) throws ClassCastException{
        if (object==this) return true;
        if (object == null || getClass() != object.getClass()) return false;

        DoubleLinkedList<?> that = (DoubleLinkedList<?>) object;

        LinkedList<String> valueCollectionThis = new LinkedList<>();
        LinkedList<String> valueCollectionThat = new LinkedList<>();

        for (LinkedList<String> llS: data){
            valueCollectionThis.addAll(llS);
        }
        for (LinkedList<String> llS: that.data){
            valueCollectionThat.addAll(llS);
        }
        if (that.data.getLast().size()==0){that.data.removeLast();}

        return valueCollectionThis.containsAll(valueCollectionThat)
        &&data.size()==that.data.size()
        &&this.linkListCapacity.equals(that.linkListCapacity);

    }
    @Override
    public int hashCode() {
        return Objects.hash(data, linkListCapacity);
    }
}

