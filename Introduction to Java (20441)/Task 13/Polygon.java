/**
 * This class represent a convex polygon in the plane.
 * @author Ben Arviv
 * @version 28/11/2020
 */
public class Polygon{
    private Point[] _vertices;
    private int _numOfVertices;
    private final int MAX_VERTICES = 10;
    
    private final int MIN_VAL = 0;
    private final int TRIANGLE_VERTICES = 3;
    private final int RECTANGLE_VERTICES = 4;
    
    /**
     * Construct a polygon with maximal array size
     */
    public Polygon(){
        _vertices = new Point[MAX_VERTICES];
    }
    
    /**
     * Adds a vertex to the first empty cell
     * @param x The x coordinate of the point
     * @param y The y coordinate of the point
     * @return True if vertex has been added
     */
    public boolean addVertex(double x, double y){
        for (int i = 0; i < _vertices.length; i++){
            if (_vertices[i] == null){
                Point p = new Point(x, y);
                _vertices[i] = new Point(p);
                _numOfVertices++;
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns a copy of the highest vertex in the polygon.
     * If there is more than one, it returns the first vertex.
     * If there is none, return Null
     * @return A copy of the highest vertex, if exist
     */
    public Point highestVertex(){
        int index = 0;
        double height = 0;
        if (_numOfVertices == 0)
            return null;
        for (int i = 0; i < _numOfVertices; i++){
            if (_vertices[i].getY() > height){
                index = i;
                height = _vertices[i].getY();
            }
        }
        return new Point(_vertices[index]);
    }
    
    /**
     * Returns a string representation of the polygon
     * @return String representation of the polygon
     */
    public String toString(){
        int i = 0;
        if (_numOfVertices == MIN_VAL)
            return "The polygon has 0 vertices.";
        String str = "The polygon has " + _numOfVertices + " vertices:\n("
        + _vertices[i];
        i++;
        for (; i < _numOfVertices; i++){
            str += "," + _vertices[i];
        }
        str += ")";
        return str;
    }
    
    /**
     * Returns the polygon's perimeter.
     * If it has two vertices, returns the length.
     * If it has one vertex or none at all, returns 0
     * @return The polygon's perimeter
     */
    public double calcPerimeter(){
        double perimeter;
        int first = 0, second = 1;
        if (_numOfVertices == 0 || _numOfVertices == 1)
            return 0;
        if (_numOfVertices == 2){
            perimeter = _vertices[first].distance(_vertices[second]);
            return perimeter;
        }
        perimeter = _vertices[first].distance(_vertices[_numOfVertices - 1]);
        for (int i = second; i < _numOfVertices; i++){
            perimeter += _vertices[i-1].distance(_vertices[i]);
        }
        return perimeter;
    }
    
    /**
     * Calculates and returns the area of the polygon
     * @return The area of the polygon
     */
    public double calcArea(){
        if (_numOfVertices < TRIANGLE_VERTICES)
            return 0;
        double area = 0;
        for (int i = 2; i < _numOfVertices; i++){
            area += this.triangleArea(_vertices[MIN_VAL], _vertices[i-1], _vertices[i]);
        }
        return area;
    }
    
    /**
     * Checks if this polygon is bigger than other polygon
     * @param Other polygon to compare with
     * @return True if this polygon is bigger than other polygon
     */
    public boolean isBigger(Polygon other){
        return (this.calcArea() > other.calcArea());
    }
    
    /**
     * Checks if a vertex is in the polygon.
     * If it's not in the polygon, returns -1
     * @param vertex The vertex to check
     * @return The index of the vertex
     */
    public int findVertex(Point vertex){
        int index = 0;
        for (int i = 0; i < _numOfVertices; i++){
            if (_vertices[i].equals(vertex)){
                index = i;
                return index;
            }
        }
        return -1;
    }
    
    /**
     * Returns the next vertex in the polygon.
     * If recieved point isn't a vertex, returns null.
     * If recieved point is the last vertex, returns the first vertex
     * @param p1 The point to check
     * @return The index of the next point, if exist
     */
    public Point getNextVertex(Point p1){
        if (_numOfVertices == 0)
            return null;
        if (_numOfVertices == 1)
            return new Point(_vertices[_numOfVertices - 1]);
        if (p1.equals(_vertices[_numOfVertices - 1]))
            return new Point(_vertices[MIN_VAL]);
        for (int i = 0; i < _numOfVertices - 1; i++){
            if (p1.equals(_vertices[i]))
                return new Point(_vertices[i+1]);
        }
        if (p1.equals(_vertices[_numOfVertices]))
            return new Point(_vertices[MIN_VAL]);
        return null;
    }
    
    /**
     * Returns the circumscribed rectangle
     * @return The circumscribed rectangle. If polygon has less than 3 vertices, returns null.
     */
    public Polygon getBoundingBox(){
        if (_numOfVertices < TRIANGLE_VERTICES)
            return null;
        Polygon rectangle = new Polygon();
        double tempX = _vertices[MIN_VAL].getX(), tempY = _vertices[MIN_VAL].getY();
        for (int i = 0; i < RECTANGLE_VERTICES; i++){
            for (int j = 0; j < _numOfVertices; j++){
                double currentX = _vertices[j].getX();
                double currentY = _vertices[j].getY();
                switch (i){
                    case 0:
                        if (currentX < tempX)
                            tempX = currentX;
                        if (currentY < tempY)
                            tempY = currentY;
                        break;
                    case 1:
                        if (currentX > tempX)
                            tempX = currentX;
                        if (currentY < tempY)
                            tempY = currentY;
                        break;
                    case 2:
                        if (currentX > tempX)
                            tempX = currentX;
                        if (currentY > tempY)
                            tempY = currentY;
                        break;
                    case 3:
                        if (currentX < tempX)
                            tempX = currentX;
                        if (currentY > tempY)
                            tempY = currentY;
                        break;
                }
            }
            rectangle.addVertex(tempX, tempY);
            tempX = _vertices[MIN_VAL].getX(); 
            tempY = _vertices[MIN_VAL].getY();
        }
        return rectangle;
    }
    
    /*
     * Calculates a triangle's area using Heron's formula
     */
    private double triangleArea(Point p1, Point p2, Point p3){
        double segA = p1.distance(p2);
        double segB = p2.distance(p3);
        double segC = p3.distance(p1);
        double perimeter = (segA + segB + segC) / 2;
        double area = perimeter * (perimeter - segA) * (perimeter - segB) * (perimeter - segC);
        return Math.sqrt(area);
    }
}
