/**
 * Representing a point according to the Polar system.
 * @author Ben Arviv
 * @version 01/11/2020
 */
public class Point{
    private double _radius;
    private double _alpha; //in degrees
    
    private final int RIGHT_ANGLE = 90;
    private final int MIN_VALUE = 0; //the point's minimal x and y values
    private final int ZERO_ANGLE = 0; //when the trigonometric angle equals to 0
    private final int RAD_TO_DEG = 180; //radians to degree converison constant 
    
     /**
     * Construct a point.  
     * If one of the parameters is negative then it should be initialized to zero
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public Point(double x, double y){
        double tan;
        x = (x <= MIN_VALUE) ? MIN_VALUE : x; //checks if x is positive
        y = (y <= MIN_VALUE) ? MIN_VALUE: y; //checks if y is positive
        _radius = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)); //calculates the radius using Pythagorean theorem
        
        if (x == MIN_VALUE)
            _alpha = RIGHT_ANGLE;
        else{
            tan = y / x;
            _alpha = this.toDegrees(Math.atan(tan)); //converts the angle to degrees
        }
    }
    
     /**
     * Copy constructor for Point.  
     * Construct a point with the same radius an angle as other point
     * @param other The point object from which to construct the new point
     */
    public Point(Point other){
        this(other.getX(), other.getY());
    }
    
    /**
     * Returns the x coordinate of the point
     * @return The x coordinate of the point
     */
    public double getX(){
        double alphaRad = this.toRadians(_alpha); //converts the angle to radians
        if (_alpha == (double)RIGHT_ANGLE)
            return _radius * ZERO_ANGLE;
        double toReturn = _radius * Math.cos(alphaRad);
        return toReturn;
    }
    
    /**
     * Returns the y coordinate of the point
     * @return The y coordinate of the point
     */
    public double getY(){
        double alphaRad = this.toRadians(_alpha);
        if (_alpha == (double)ZERO_ANGLE)
            return _radius * ZERO_ANGLE;
        return _radius * Math.sin(alphaRad);
    }
    
     /**
     * Sets the x coordinate of the point.  
     * If a negative number is received, then x remain unchanged
     * @param num The new x coordinate
     */
    public void setX(double num){
        
        if (num >= 0){
            _radius = Math.sqrt(Math.pow(num, 2) + Math.pow(this.getY(), 2)); //calculates the radius using Pythagorean theorem
            _alpha = this.setAlpha('x', num);
        }
    }
    
     /**
     * Sets the y coordinate of the point.  
     * If a nagative number is received, then y remain unchanged
     * @param num The new y coordinate
     */
    public void setY(double num){
        if (num >= 0){
            _radius = Math.sqrt(Math.pow(this.getX(), 2) + Math.pow(num, 2)); //calculates the radius using Pythagorean theorem
            _alpha = this.setAlpha('y', num);
        }
    }
    
     /**
     * Returns a string representation of this point
     * @return String representation of this point
     */
    public String toString(){
        return "(" + (double) Math.round(this.getX() * 10000 / 10000) + "," + (double) Math.round(this.getY() * 10000 / 10000) + ")";
    }
    
    /**
     * Check if this point equals other point
     * @param other The point to be compared with this point
     * @return True if the points are equal
     */
    public boolean equals(Point other){
        return this.getX() == other.getX() && this.getY() == other.getY();
    }
    
    /**
     * Checks if this point is above other point
     * @param other The point to be compared with this point
     * @return True if this point is above other point
     */
    public boolean isAbove(Point other){
        return this.getY() > other.getY();
    }
    
    /**
     * Checks if this point is under other point
     * @param other The point to be compared with this point
     * @return True if this point is under other point
     */
    public boolean isUnder(Point other){
        return other.isAbove(this);
    }
    
    /**
     * Checks if this point is to the left of other point
     * @param other The point to be compared with this point
     * @return True if this point is left to other point
     */
    public boolean isLeft(Point other){
        double thisX = this.getX();
        double otherX = other.getX();
        return thisX < otherX;
    }
    
    /**
     * Checks if this point is to the right of other point
     * @param other The point to be compared with this point
     * @returm True if this point is right to other point
     */
    public boolean isRight(Point other){
        return other.isLeft(this);
    }
    
     /**
     * Calculates the distance between this point and other point
     * @param p The point to calculate the distance from
     * @return The distance between p and this point
     */
    public double distance(Point p){
        double subY = this.getY() - p.getY(); //subtraction of the Y coordinates
        double subX = this.getX() - p.getX(); //subtraction of the X coordinates
        double powY = Math.pow(subY, 2); //raises subY to the power of 2
        double powX = Math.pow(subX, 2); //raises subX to the power of 2
        return Math.sqrt(powY + powX);
    }
    
     /**
     * Moves the x coordinate by dx and the y coordinate by dy.  
     * If movement moves the point outside of the first quadrant
     * then the point will remain at the same place and won't move
     * @param dx The amount to move in the x direction
     * @param dy The amount to move in the y direction
     */
    public void move(double dx, double dy){
        double movedX = this.getX() + dx;
        double movedY = this.getY() + dy;
        if (movedX >= 0 && movedY >= 0){
            this.setX(movedX);
            this.setY(movedY);
        }
    }
    
    /*
     * Converts the received angle from degrees to radians.
     */
    private double toRadians(double alpha){
        double retAlpha = alpha * Math.PI / RAD_TO_DEG;
        return retAlpha;
    }
    
    /*
     * Converts the received angle from radians to degrees.
     */
    private double toDegrees(double alpha){
        double retAlpha = alpha * RAD_TO_DEG / Math.PI;
        return retAlpha;
    }
    
    /*
     * Sets the angle according to x and y coordinates.
     */
    private double setAlpha(char ch, double d1){
        double tan;
        double radPow = Math.pow(_radius, 2); //raises radius to the power of 2
        double valPow = Math.pow(d1, 2); //raises x or y value to the power of 2
        double val = Math.sqrt(radPow - valPow); //calculates the missing value (x or y)
        if (ch == 'x'){
            if (d1 == (double)MIN_VALUE)
                return RIGHT_ANGLE;
            tan = val / d1;
            return this.toDegrees(Math.atan(tan));
        }
        else{
            if (d1 == (double)MIN_VALUE)
                return ZERO_ANGLE;
            tan = d1 / val;
            return this.toDegrees(Math.atan(tan));
        }
    }
}
