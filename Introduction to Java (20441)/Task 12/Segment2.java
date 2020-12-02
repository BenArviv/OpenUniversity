/*/**
 * Segment2 represents a line (parallel to the x-axis) using a center point and length.
 * @author Ben Arviv
 * @version 03/11/2020
 */
public class Segment2{
    private Point _poCenter;
    private double _length;
    
    private final double MIN_VAL = 0; //minimal value after movement
    
    /**
     * Constructs a new segment using 4 specified x y coordinates: 
     * two coordinates for the left point and two coordinates for the right point.
     * If the y coordinates are different, change the y of the right point to be equal to the y of the left point
     * @param leftX X value of the left point
     * @param leftY Y value of the left point
     * @param rightX X value of the right point
     * @param rightY Y value of the rifht pointucts a new segment using two Points. 
     * If the
     */
    public Segment2(double leftX, double leftY, double rightX, double rightY){
        if (leftY != rightY)
            rightY = leftY;
        double centerX = (leftX + rightX) / 2;
        double centerY = (leftY + rightY) / 2;
        _poCenter = new Point(centerX, centerY);
        _length = rightX - leftX;
    }
    
    /**
     * Constructs a new segment using a center point and the segment length
     * @param poCenter the Center Point
     * @param length the segment length
     */
    public Segment2(Point poCenter, double length){
        _poCenter = poCenter;
        _length = length;
    }
    
    /**
     * Constr y coordinates are different, change the y of the right point 
     * to be equal to the y of the left point
     * @param left the left point of the segment
     * @param right the right point of the segment
     */
    public Segment2(Point left, Point right){
        if(left.getY() != right.getY())
            right.setY(left.getY());
        double center = (left.getX() + right.getX()) / 2;
        _poCenter = new Point(center, left.getY());
        _length = right.getX() - left.getX();
    }
    
    /**
     * Copy Constructor. Construct a segment using a reference segment
     * @param other the reference segment
     */
    public Segment2(Segment2 other){
        this(other.getPoLeft(), other.getPoRight());
    }
    
    /**
     * Change the segment size by moving the right point by delta. 
     * Will be implemented only for a valid delta: only if the new right point remains the right point
     * @param delta the length change
     */
    public void changeSize(double delta){
        double newCenter, newRight;
        newRight = this.getPoRight().getX() + delta;
        if (newRight >= this.getPoLeft().getX()){
            newCenter = (newRight + this.getPoLeft().getX()) / 2;
            _poCenter.setX(newCenter);
            _length += delta;
        }
    }
    
    /**
     * Check if the reference segment is equal to this segment
     * @param other the reference segment
     * @return True if the reference segment is equal to this segment
     */
    public boolean equals(Segment2 other){
        return (_poCenter.equals(other._poCenter) && _length == other._length);
    }
    
    /**
     * Returns the segment length.
     * @return The segment length
     */
    public double getLength(){
        return _length;
    }
    
    /**
     * Returns the left point of the segment
     * @return The left point of the segment
     */
    public Point getPoLeft(){
        double leftX = _poCenter.getX() - (_length / 2);
        return new Point(leftX, _poCenter.getY());
    }
    
    /**
     * Returns the right point of the segement
     * @return The right point of the segment
     */
    public Point getPoRight(){
        double leftX = _poCenter.getX() + (_length / 2);
        return new Point(leftX, _poCenter.getY());
    }
    
    /**
     * Checks if the segment is above a reference segment
     * @param other the reference segment
     * @return True if this segment is above the reference segment
     */
    public boolean isAbove(Segment2 other){
        return (_poCenter.isAbove(other._poCenter));
    }
    
    /**
     * Check if this segment is bigger than a reference segment
     * @param other the reference segment
     * @return True if this segment is bigger than the reference segment
     */
    public boolean isBigger(Segment2 other){
        return (_length > other._length);
    }
    
    /**
     * Checks if this segment is left of a received segment
     * @param other the reference segment
     * @return True if this segment is left to the reference segment
     */
    public boolean isLeft(Segment2 other){
        return (this.getPoRight().isLeft(other.getPoLeft())); 
    }
    
    /**
     * Checks if this segment is right of a received segment
     * @param other the reference segment
     * @return True if this segment is right to the reference segment
     */
    public boolean isRight(Segment2 other){
        return (this.getPoLeft().isRight(other.getPoRight()));
    }
    
    /**
     * Checks if this segment is under a reference segment.
     * @param other the reference segment
     * @return True is this segment is under the reference segment
     */
    public boolean isUnder(Segment2 other){
        return other.isAbove(this);
    }
    
    /**
     * Move the segment horizontally by delta.
     * Will be implemented only for a valid delta
     * @param delta the displacement size
     */
    public void moveHorizontal(double delta){
        if (this.getPoLeft().getX() + delta < MIN_VAL)
            delta = MIN_VAL;
        _poCenter.move(delta, MIN_VAL);
    }
    
    /**
     * Move the segment vertically by delta.
     * Will be implemented only for a valid delta
     * @param delta the displacement size
     */
    public void moveVertical(double delta){
        if (this.getPoLeft().getY() + delta < MIN_VAL)
            delta = MIN_VAL;
        _poCenter.move(MIN_VAL, delta);
    }
    
    /**
     * Returns the overlp size of this segment and a reference segment
     * @param other the reference segment
     * @return The overlap size
     */
    public double overlap(Segment2 other){
        if (this.getPoRight().getX() < other.getPoLeft().getX())
            return MIN_VAL;
        if (this.getPoLeft().getX() > other.getPoRight().getX())
            return MIN_VAL;
        boolean toLeft = this.getPoLeft().isLeft(other.getPoLeft());
        boolean toRight = this.getPoRight().isRight(other.getPoRight());
        if (toLeft)
            if (toRight)
                return other.getLength();
            else
                return this.getPoRight().getX() - other.getPoLeft().getX();
        else{
            if (!toRight)
                return this.getLength();
            return other.getPoRight().getX() - this.getPoLeft().getX();
        }
    }
    
    /**
     * Check if a point is located on the segment
     * @param p a point to be checked
     * @return True if p is on this segment
     */
    public boolean pointOnSegment(Point p){
        boolean asHigh = this.getPoLeft().getY() == p.getY();
        boolean inRangeLeft = this.getPoLeft().getX() <= p.getX();
        boolean inRangeRight = this.getPoRight().getX() >= p.getX();
        return asHigh && (inRangeLeft && inRangeRight);
    }
    
    /**
     * Return a string representation of this segment in the format (3.0,4.0)---(3.0,6.0)
     * @return String representation of this segment
     */
    public String toString(){
        return this.getPoLeft() + "---" + this.getPoRight();
    }
    
    /**
     * Compute the trapeze perimeter, which constructed by this segment and a reference segment
     * @param other the reference segment
     * @return The trapeze perimeter
     */
    public double trapezePerimeter(Segment2 other){
        double leftDist = this.getPoLeft().distance(other.getPoLeft());
        double rightDist = this.getPoRight().distance(other.getPoRight());
        double perimeter = leftDist + rightDist + this.getLength() + other.getLength();
        return perimeter;
    }
}
