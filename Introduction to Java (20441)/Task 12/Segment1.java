/**
 * Representing a segment that is parallel to the X-axis.
 * @author Ben Arviv
 * @version 03/11/2020
 */
public class Segment1{
    private Point _poLeft;
    private Point _poRight;
    
    private final double MIN_VAL = 0; //minimal value after movement
    private final int NO_OVERLAP = 0; //if there is no overlap
    
    /**
     * Constructs a segment from two received points.
     * If a segment isn't parallel to the X-axis, the right
     * y coordinate is set according to the y left coordinate
     * @param left The left point of the segment
     * @param right The right point of the segment
     */
    public Segment1(Point left, Point right){
        if (left.getY() != right.getY())
            right.setY(left.getY());
        _poLeft = new Point(left);
        _poRight = new Point(right);
    }
    
    /**
     * Construct a segment from received x and y coordinates.
     * If left and right y coordinates don't match, right y coordinate
     * is set according to the y left coordinate
     * @param leftX The left x coordinate
     * @param leftY The left y coordinate
     * @param rightX The right x coordinate
     * @param rightY The right y coordinate
     */
    public Segment1(double leftX, double leftY, double rightX, double rightY){
        if (rightY != leftY)
            rightY = leftY;
        _poLeft = new Point(leftX, leftY);
        _poRight = new Point(rightX, rightY);
    }
    
    /**
     * Copy constructor for Segment1.
     * Construct a segment with the same left and right points
     * as other segment
     * @param other The segment1 object from which to construct the new segment
     */
    public Segment1(Segment1 other){
        this(other._poLeft, other._poRight);
    }
    
    /**
     * Returns the left point of the segment
     * @return The left point of the segment
     */
    public Point getPoLeft(){
        return new Point(_poLeft);
    }
    
    /**
     * Returns the right point of the segment
     * @return The right point of the segment
     */
    public Point getPoRight(){
        return new Point(_poRight);
    }
    
    /**
     * Returns the length of the segment
     * @return The length of the segment
     */
    public double getLength(){
        double length = _poLeft.distance(_poRight);
        return length;
    }
    
    /**
     * Returns a string representation of the segment
     * @return String representation of the segment
     */
    public String toString(){
        return _poLeft + "---" + _poRight;
    }
    
    /**
     * Checks if this segment equals other segment
     * @param other The segment to be compared with this segment
     * @return True if the segments are equal
     */
    public boolean equals(Segment1 other){
        boolean isLeft = _poLeft.equals(other._poLeft);
        boolean isRight = _poRight.equals(other._poRight);
        return isLeft && isRight;
    }
    
    /**
     * Checks if this segment is above other segment
     * @param other The segment to be compared with this segment
     * @return True if this segment is above other segment
     */
    public boolean isAbove(Segment1 other){
        return _poLeft.isAbove(other._poLeft);
    }
    
    /**
     * Checks if this segment is under other segment
     * @param other The segment to be compared with this segment
     * @return True if this segment is under other segment
     */
    public boolean isUnder(Segment1 other){
        return other.isAbove(this);
    }
    
    /**
     * Checks if the whole segment is left to other segment
     * @param other The segment to be compared with this segment
     * @return True if this segment is to the left of other segment
     */
    public boolean isLeft(Segment1 other){
        return _poRight.isLeft(other._poLeft);
    }
    
    /**
     * Checks if the whole segment is right to other segment
     * @param other The segment to be compared with this segment
     * @return True if this segment is to the right of other segment
     */
    public boolean isRight(Segment1 other){
        return _poLeft.isRight(other._poRight);
    }
    
    /**
     * Moves the segment vertically by delta.
     * If movement moves the segment outside of the first quadrant
     * then the segment will remain in the same place
     * @param delta The amount to move vertically
     */
    public void moveVertical(double delta){
        double currentY = _poLeft.getY();
        if (currentY + delta < MIN_VAL)
            delta = MIN_VAL;
        _poLeft.move(MIN_VAL, delta);
        _poRight.move(MIN_VAL, delta);
    }
    
    /**
     * Moves the segment horizontally by delta.
     * If movement moves the segment outside of the first quadrant
     * then the segment will remain in the same place
     * @param delta The amount to move horizontally
     */
    public void moveHorizontal(double delta){
        double currentX = _poLeft.getX();
        if (currentX + delta < MIN_VAL)
            delta = MIN_VAL;
        _poLeft.move(delta, MIN_VAL);
        _poRight.move(delta, MIN_VAL);
    }
    
    /**
     * Resizes the segment by delta.
     * If after the resize, the right point is to the left of the left point,
     * the resize is cancelled and the segment remain unchanged
     * @param delta The amount to resize the segment
     */
    public void changeSize(double delta){
        double currentX = _poRight.getX();
        if (currentX + delta < _poLeft.getX())
            delta = MIN_VAL;
        _poRight.setX(currentX + delta);
    }
    
    /**
     * Checks if a point is on the segment
     * @param p The point to check if it's on the segment
     * @return True if p is on the segment
     */
    public boolean pointOnSegment(Point p){
        boolean asHigh = !(_poLeft.isAbove(p) && _poLeft.isUnder(p));
        boolean inRangeLeft = _poLeft.getX() <= p.getX();
        boolean inRangeRight = _poRight.getX() >= p.getX();
        return asHigh && (inRangeLeft && inRangeRight);
    }
    
    /**
     * Checks if this segment is longer than other segment
     * @param other The segment to be compared with
     * @return True if this segment is longer then other segment
     */
    public boolean isBigger(Segment1 other){
        return this.getLength() > other.getLength();
    }
    
    /**
     * Returns the overlap length between two segments
     * @param other The segment to check the overlap with
     * @return The length of the overlap between the two segments
     */
    public double overlap(Segment1 other){
        if (_poRight.getX() < other._poLeft.getX())
            return NO_OVERLAP;
        if (_poLeft.getX() > other._poRight.getX())
            return NO_OVERLAP;
        boolean toLeft = _poLeft.getX() < other._poLeft.getX();
        boolean toRight = _poRight.getX() > other._poRight.getX();
        if (toLeft)
            if (toRight)
                return other.getLength();
            else
                return _poRight.getX() - other._poLeft.getX();
        else{
            if (!toRight)
                return this.getLength();
            return other._poRight.getX() - _poLeft.getX();
        } 
    }
    
    /**
     * Returns the perimeter of a trapeze built by this and 
     * other segment
     * @param other The segment that build the trapeze with 
     * this segment
     * @return The perimeter of that trapeze
     */
    public double trapezePerimeter(Segment1 other){
        double leftDist = _poLeft.distance(other._poLeft);
        double rightDist = _poRight.distance(other._poRight);
        double perimeter = leftDist + rightDist + this.getLength() + other.getLength();
        return perimeter;
    }
}
