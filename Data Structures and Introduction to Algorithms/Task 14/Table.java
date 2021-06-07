public class Table {
    private int[] _tab;
    private static int _m;
    private int _halfM;
    private static int _elements;
    private static int[] _exist = new int[Task.UPPERBOUND];

    public int _count;

    public Table(int m){
        _tab = new int[m];
        _m = m;
        _halfM = (int)Math.ceil((double)_m / 2);
        _elements = 0;
        _count = 0;

        for (int i = 0; i < m; i++) {
            _tab[i] = -1;
                Task.places++; // Line: 18
        }
    }

    private int h(int k, int i){ // Θ(1)
        int key = (k % _m + i * (1 + (k % _halfM))) % _m; // h(k, i) = (k mod m + i(1 + (k mod m/2))) mod m
            Task.places++; // Line: 24
        return key;
    }

    public void insert(int k) {
        for (int i = 1; i <= _m; i++) {
            int j = h(k, i);
            Task.places++; // Line: 31
            Task.comps++; // Lines: 30, 34
            if (_tab[j] != -1){
                Task.comps++; // Line: 36
                if (_tab[j] == k)
                    break;
            }
            else {
                _tab[j] = k;
                _elements++;
                _exist[k]++;
                Task.places += 3; // Lines: 40-42
                if (_exist[k] == 1) {
                    _count++;
                    Task.places++; // Line: 45
                    Task.comps++; // Line: 44
                }
                break;
            }
        }
    }

    public static double getLoadFactor(){
        double loadFactor = (double)_elements / _m;
        return loadFactor;
    }



}
