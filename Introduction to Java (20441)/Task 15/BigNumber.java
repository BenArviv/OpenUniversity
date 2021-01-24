/**
 * Task 15
 * This class represents a linked list of integers,that represents a 
 * number that is bigger than 'long' type number.
 * @author Ben Arviv
 * @version 16/01/21
 */
public class BigNumber{
    private IntNode _head;

    private final int INITIAL = 0;

    /**
     * A constructor for BigNumber.
     * Time complexity: O(1), Space complexity: O(1)
     * Creates a new list that includes only the initial number
     */
    public BigNumber(){
        _head = new IntNode(INITIAL);
    }

    /**
     * A constructor for BigNumber.
     * Gets a long number, and represents it as a BigNumber list
     * Time complexity: O(n), Space complexity: O(n)
     * @param num A number of type 'long'
     */
    public BigNumber(long num){
        int set = (int)(num % 10);
        _head = new IntNode(set);
        IntNode curr = _head;
        num /= 10;
        
        while (num  > 0){
            set = (int)(num % 10);
            IntNode temp = new IntNode(set);
            curr.setNext(temp);
            curr = curr.getNext();
            num /= 10;
        }
    }

    /**
     * A copy constructor for BigNumber
     * Time complexity: O(n), Space complexity: O(n)
     * @param other A list of big number to copy from
     */
    public BigNumber(BigNumber other){
        if (other.empty())
            return;
            
        IntNode temp1 = new IntNode(INITIAL);
        IntNode temp2 = other._head;
        IntNode curr = new IntNode(temp2.getValue(), temp1);
        _head = curr;
        temp2 = temp2.getNext();
        
        while (temp2 != null){ // clones the list
            temp1 = new IntNode(temp2.getValue());
            curr.setNext(temp1);
            temp2 = temp2.getNext();
            curr = curr.getNext();
        }
    }

    /**
     * A recursive method that returns a string representation
     * of the big number list
     * Time complexity: O(n), Space complexity: O(1)
     * @return The big number as a string
     */
    public String toString(){
        return this.toString(_head);
    }

    /**
     * This method gets another big number and compare it to 
     * the current big number
     * Time complexity: O(n), Space complexity: O(1)
     * @param other Other BigNumber to compare to this one
     * @return 0 if equals, 1 if this bigger than other and -1 if the opposite
     */
    public int compareTo(BigNumber other){
        if (this.length() > other.length())
            return 1;
        if (this.length() < other.length())
            return -1;
            
        this.flipList();
        other.flipList();
        int result = 0;
        IntNode temp1 = _head, temp2 = other._head;
        
        while (temp1 != null){
            if (temp1.getValue() > temp2.getValue()){
                result = 1;
                break;
            }
            if (temp1.getValue() < temp2.getValue()){
                result = -1;
                break;
            }
            temp1 = temp1.getNext();
            temp2 = temp2.getNext();
        }
        
        this.flipList();
        other.flipList();
        return result;
    }

    /**
     * An adding function between two big numbers.
     * Gets a big number and adds the values of this and other big number,
     * if sum is bigger than 10, adds according to sum rules
     * Time complexity: O(n), Space complexity: O(n)
     * @param other Another big number to add to this big number
     * @return A big number which is the sum of the two big numbers
     */
    public BigNumber addBigNumber(BigNumber other){
        BigNumber summedList = new BigNumber();
        IntNode newTemp = summedList._head;
        IntNode next, last = new IntNode(INITIAL);
        IntNode temp1 = _head;
        IntNode temp2 = other._head;
        int sum = 0;
        
        if (other.empty())
            return this;
        while (temp1 != null || temp2 != null){
            if (temp1 != null && temp2 != null){
                sum += temp1.getValue() + temp2.getValue();
                
                if (sum < 10 && sum > -10){ 
                    newTemp.setValue(sum);
                    sum = 0;
                }
                else{ // sum is a two digit number
                    newTemp.setValue(sum % 10);
                    sum /= 10; 
                }
                last = newTemp;
                next = new IntNode(INITIAL);
                newTemp.setNext(next);
                newTemp = newTemp.getNext();
                temp1 = temp1.getNext();
                temp2 = temp2.getNext();
            }
            else if (temp1 == null){ // end of this list, adds the other's rest digits
                sum += temp2.getValue();
                
                if (sum < 10 && sum > -10){
                    newTemp.setValue(sum);
                    sum = 0;
                }
                else{
                    newTemp.setValue(sum % 10);
                    sum /= 10;
                }
                last = newTemp;
                next = new IntNode(INITIAL);
                newTemp.setNext(next);
                newTemp = newTemp.getNext();
                temp2 = temp2.getNext();
            }
            else if (temp2 == null){ // end of other list, adds this list's rest digits
                sum += temp1.getValue();
                
                if (sum < 10 && sum > -10){
                    newTemp.setValue(sum);
                    sum = 0;
                }
                else{
                    newTemp.setValue(sum % 10);
                    sum /= 10;
                }
                last = newTemp;
                next = new IntNode(INITIAL);
                newTemp.setNext(next);
                newTemp = newTemp.getNext();
                temp1 = temp1.getNext();
            }
        }
        
        if (sum != 0){ // if last sum was bigger than 10
            while (sum != 0){
                if (sum < 10 && sum > -10){
                    newTemp.setValue(sum);
                    break;
                }
                else{
                    newTemp.setValue(sum % 10);
                    sum /= 10;
                }
            }
        }
        
        if (newTemp.getValue() == 0) // deletes last node if has a value of 0 (translates to first digit at toString method)
            last.setNext(null);
            
        return summedList;
    }

    /**
     * This method adds a big number of type 'long' to another BigNumber
     * Time complexity: O(n), Space complexity: O(n)
     * @param num A big number of type 'long' to add to this BigNumber
     * @return A big number which is the sum of this big number and the recieved long num
     */
    public BigNumber addLong(long num){
        if (num == 0)
            return this;
        if (this.empty())
            return new BigNumber(num);
        
        BigNumber result = new BigNumber();
        int sum = 0;
        IntNode next,
                temp = _head,
                newTemp = result._head,
                last = new IntNode(INITIAL);
        while (num != 0 || temp != null){
            next = new IntNode(INITIAL);
            newTemp.setNext(next);
            if (num != 0 && temp != null){
                sum += temp.getValue() + (int)(num % 10);
                temp = temp.getNext();
                num /= 10;
            }
            else if (num == 0){
                sum += temp.getValue();
                temp = temp.getNext();
            }
            else{ // temp == null
                sum += (int)(num % 10);
                num /= 10;
            }
            if (sum < 10){
                newTemp.setValue(sum);
               sum = 0;
            }
            else{ 
                newTemp.setValue(sum % 10);
                sum /= 10;
            }
            if (newTemp.getValue() != 0)
                last = newTemp;
            newTemp = newTemp.getNext();
        }
        if (last != null)
            last.setNext(null);
        return result;
    }
    
    /**
     * This method gets another big number and subtract the smaller from the bigger
     * Time complexity: O(n), Space complexity: O(n)
     * @param other Big number for subtraction
     * @return New big number that represents the subtraction between the two big numbers
     */
    public BigNumber subtractBigNumber(BigNumber other){
        if (other.empty())
            return this;
        BigNumber result = new BigNumber(),
                  thisBigNumber = new BigNumber(this),
                  otherBigNumber = new BigNumber(other);
        int whichIsBigger = this.compareTo(other), sub, bigger = 0, smaller = 0;
        IntNode biggerTemp, smallerTemp, next,
                last = new IntNode(INITIAL), 
                newTemp = result._head;
        if (whichIsBigger == 0)
            return result;
        else if (whichIsBigger == -1){
            biggerTemp = otherBigNumber._head;
            smallerTemp = thisBigNumber._head;
        }
        else{
            biggerTemp = thisBigNumber._head;
            smallerTemp = otherBigNumber._head;
        } 
        while (smallerTemp != null){
            bigger = biggerTemp.getValue();
            smaller = smallerTemp.getValue();
            if (smaller > bigger){
                bigger += 10;
                next = biggerTemp.getNext();
                while (next.getNext() != null && next.getValue() == 0){
                    next.setValue(9);
                    last = next;
                    next = next.getNext();
                } // end of while
                next.setValue(next.getValue() - 1);
            } // end of if
            sub = bigger - smaller;
            newTemp.setValue(sub);
            if (newTemp.getValue() != 0)
                last = newTemp;
            newTemp.setNext(new IntNode(INITIAL));
            newTemp = newTemp.getNext();
            biggerTemp = biggerTemp.getNext();
            smallerTemp = smallerTemp.getNext();
        } // end of while
        while (biggerTemp != null){
            bigger = biggerTemp.getValue();
            newTemp.setValue(bigger);
            if (bigger != 0)
                last = newTemp;
            biggerTemp = biggerTemp.getNext();
            newTemp.setNext(new IntNode(INITIAL));
            newTemp = newTemp.getNext();    
        } // end of while
        if (last.getNext() != null && last.getNext().getValue() == 0)
            last.setNext(null);

        return result;
    }
    
    /**
     * This method gets a big number and multiply it by this big number, using long multiplication
     * Time complexity: O(n^2), Space complexity: O(n)
     * @param other Another big number to multiply by
     * @return A big number that is the product of the two big numbers
     */
    public BigNumber multBigNumber (BigNumber other){
        BigNumber product = new BigNumber(), 
                  current = new BigNumber();
        IntNode thisTemp, otherTemp, currentTemp, temp, 
                last = new IntNode(INITIAL);
        int count = 0, mul = 0, thisVal, otherVal;
        if (this.length() >= other.length()){
            thisTemp = _head;
            otherTemp = other._head;
        }
        else{
            thisTemp = other._head;
            otherTemp = _head;
        }
        if (other.empty() || this.empty())
            return product;
        temp = thisTemp;
        currentTemp = current._head;
        while (otherTemp != null){
            for (int i = 0; i < count; i++){
                currentTemp.setValue(INITIAL);
                currentTemp.setNext(new IntNode(INITIAL));
                currentTemp = currentTemp.getNext();
            }
            otherVal = otherTemp.getValue();
            while (thisTemp != null){
                thisVal = thisTemp.getValue();
                mul += thisVal * otherVal;
                if (mul < 10){
                    currentTemp.setValue(mul);
                    mul = 0;
                }
                else {
                    currentTemp.setValue(mul % 10);
                    mul /= 10;
                }
                if (currentTemp.getValue() != 0)
                    last = currentTemp;
                thisTemp = thisTemp.getNext();
                currentTemp.setNext(new IntNode(INITIAL));
                currentTemp = currentTemp.getNext();
            }
            if (last.getNext() != null && last.getNext().getValue() == 0)
                last.setNext(null);
            product = product.addBigNumber(current);
            current = new BigNumber();
            currentTemp = current._head;
            otherTemp = otherTemp.getNext();
            thisTemp = temp;
            count++;
        }
        return product;
    } // end of method

    /*
     * A private method that flips the nodes in the list
     */
    private void flipList(){
        if (_head == null || _head.getNext() == null) 
            return;
        IntNode prev = null;
        IntNode current = _head;
        IntNode next;
        while (current != null) {
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }
        _head = prev;
    }

    /*
     * A private recursive method.
     * Goes throgh the list and returns the value from last to first
     */
    private String toString(IntNode node){
        if (node == null)
            return "";
        return toString(node.getNext()) + "" + node.getValue();
    }

    /*
     * Private method that checks if the list is empty
     */
    private boolean empty(){
        return _head == null || (_head.getValue() == 0 && _head.getNext() == null);
    }

    /*
     * Private method that returns the amount of nodes in the list
     */
    private int length()
    {
        IntNode temp = _head;
        int count = 0;
        while (temp != null)
        {
            count++;
            temp = temp.getNext();
        }
        return count;
    } 
}
