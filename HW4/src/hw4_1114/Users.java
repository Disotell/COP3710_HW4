/******************************************************************
 * Author: 1114
 * Date: 3/23/2014
 * Assignment: HW4
 * Class:COP 3720
 * Professor: Dr. Dahai Guo
 * University: Florida Gulf Coast University
 ******************************************************************/
package hw4_1114;
public class Users {
    

    public static class Name {
      private String _first, _last;

      public String getFirst() { return _first; }
      public String getLast() { return _last; }

      public void setFirst(String s) { _first = s; }
      public void setLast(String s) { _last = s; }
    }

    
    private Name _name;
    private boolean _isVerified;
    private byte[] _userImage;
    
    public Name getName() { return _name; }
    public boolean isVerified() { return _isVerified; }
   
    public byte[] getUserImage() { return _userImage; }

    public void setName(Name n) { _name = n; }
    public void setVerified(boolean b) { _isVerified = b; }
    public void setUserImage(byte[] b) { _userImage = b; }
}