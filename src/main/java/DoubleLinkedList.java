import java.rmi.server.ExportException;
import java.sql.Array;
import java.util.*;

public class DoubleLinkedList<T extends Comparable<T>> implements Collection<String> {// Дженерик прикрутить была идея Дато, чтобы если что -
    //сделать возможным работу не только со строками и можно было сравнивать объекты, но я пока и только со строками буксую.

    public LinkedList<LinkedList<String>> data;
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
        public boolean hasNext() {//Здесь по хорошему надо обработать ошибку NoSuchElementException, поскольку при генерировании
            // новых вложенных линкед листов будут пустые хвосты с null, соотвветственно она должна выскочить. Я сначала думал их условиями
            // в методах вычистить, но получается хрень
            // поскольку они зачищают пустые хвосты на стадии создания и в data ничего не добавляется(см. конструктор и метод add).

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
       // if(data.getLast().size()==0){data.removeLast();}// Вот таким макаром хотел их чистить, но идея оказалась хреновая.
        return new DoubleIterator();
    }


    @Override
    public int size() {
        Integer count = 0;//здесь реализуем size по количеству строк во вложенных линкедлистах.
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

        for (LinkedList<String> llS : data) {//Здесь циклом пробегаем по data и ищем совпадения строк во вложенных линкед листах
            if (llS.contains(o))
                return true;
        }
        return false;
    }


    @Override
    public Iterator<String> iterator() {
       // if(data.getLast()==null){data.removeLast();}//опять пытался вычистить пустые хвосты
        return  getIterator();
    }


    @Override
    public boolean add(String string) {
        if (string==null){
            throw new RuntimeException ("The string can't be a null");//это робкие попытки понять как быть с ошибками, но
                                                                      //эта тема мне пока плохо дается, я над этим работаю
        }
        this.data.getLast().add(string);// корень зла - как по другому набить вложенные линкед листы не придумал,
                                        // но как только вылажу за linkListCapacity, прицепляю новый линкед лист
                                        // если есть чем его набить - хорошо, если нет, остается пустой хвос, вываливающий ошибку при итерации
        if ((data.getLast().size() == linkListCapacity)) {
            data.add(new LinkedList<>());
        }return true;

    }

    @Override
    public String[][] toArray() {//я художник, я так вижу( набиваем двумерный массив содержимым наших линкедлистов

        String[][] arr = new String[data.size()][];
        for (int k = 0; k < data.size(); k++) {
            LinkedList<String> ll = data.get(k);
            String[] strArr = new String[ll.size()];

            for (int j = 0; j < strArr.length; j++) {
                strArr[j] = ll.get(j);
            }
            arr[k]=strArr;
        }return arr;


    }

    @Override
    public <T> T[] toArray(T[] a) {//Вот с этим методом никак не могу разобраться, как его можно переопределить. Насколько я понимаю,
        //он на входе принимает массив, и если массив больше "а", то надо создать новый массив а, в который он поместится, если массив
        //меньше а, то туда копируется входящий массив, а на хвостах будут null.
        // Лучше всего, когда размеры совпадают, как я понимаю. Не до конца понятна практическая польза, но это мой недостаток опыта.

      T[][] b=(T[][])Arrays.copyOf(data.toArray(),data.size());//???Это просто заглушка
      /*if(data.size()>a.length){//Это внутренняя реализация этого метода, но как его прикрутить к моему случаю - не пойму
          T[][] d =
      }
      if (a.length < b.)
                // Make a new array of a's runtime type, but my contents:
                return (T[]) Arrays.copyOf(elementData, size, a.getClass());
            System.arraycopy(elementData, 0, a, 0, size);
            if (a.length > size)
                a[size] = null;
            return a;
        }*/
        return (T[]) b;
    }

    @Override
    public boolean remove(Object o) {

        for (LinkedList<String> llS : data) {/*Прошлись циклом, удалили объект, если размер влоденного листа стал нулевым,
        удалили пустой лист*/
            llS.remove(o);}
            data.removeIf(llS -> llS.size() == 0);

        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {

        LinkedList<String> tempLl = new LinkedList<>();/*Разворачиваем коллекцию в одномерный лист и сравниваем с
        "с", если все переданное в метод совпало, с шаблоном, возвращаем true*/
        for (LinkedList<String> llS : data) {
            tempLl.addAll(llS);
            if (tempLl.containsAll(c)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {

        //if(data.getLast().size()==0){data.removeLast();} опять чистим хвосты
        for (String str : c) {//по принципу метода add берем входящую колллекцию и запихиваем ее по шаблону в реципиента
            data.getLast().add(str);//вот после того, как этот метод отработал, при попытке распечатать результат все валится,
                                    // потомучто NoSuchElementException в конце присоединенного списка.
            if (data.getLast().size() > linkListCapacity) {
                data.add(new LinkedList<>());
            }
        }return true;
    }
    @Override
    public boolean removeAll(Collection<?> c) {
        for(LinkedList<String> llS:data){//По принципу обычного remove проходим по коллекции, вычищаем что нужно,

            llS.removeAll(c);
          }
        data.removeIf(llS -> llS.size() == 0);//пустые листы удаляем
            return true;
    }


    @Override
    public boolean retainAll(Collection<?> c) {
        for(LinkedList<String> llS:data){//то же самое, но наоборот
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
    public boolean equals(Object object) throws ClassCastException{//переопределил equals работает, но может глючить из-за this и that size,
        if (object==this) return true;                              // завтра проверю.
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

