import java.util.LinkedList;
public class Cache {
        private int dim;
        public LinkedList<Object> cache;
        public Cache(int d1) {
            this.dim = d1;
            this.cache = new LinkedList<>();
        }

        public void clear() {
            this.cache.clear();
        }

        public int getNEntries(){ //return the real size, how many words have been inserted
            return this.cache.size();
        }

        public int getDim(){
            return this.dim;
        }

        public LinkedList getCache(){
            return this.cache;
        }

        public void addItem(Object o){
            this.cache.addFirst(o);
        }

        public boolean existsItem(Object o){
            boolean e;
            if(this.cache.contains(o)){
                e = true;
            }else{
                e = false;
            }
            return e;
        }

        public void removeLast(){
            this.cache.removeLast();
        }

        public void remove(Object o){
            this.cache.remove(o);
        }

        public void existedItem(Object o){
            this.cache.remove(o);
            this.cache.addFirst(o);
        }
}


