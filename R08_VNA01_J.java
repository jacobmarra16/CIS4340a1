Rule 08. Visibility and Atomicity (VNA)
VNA01-J. Ensure visibility of shared references to immutable objects Given the
non-compliant code below:
  final class Foo {
    private Helper helper;
 
    public Helper getHelper() {
      return helper;
    }
 
    public void setHelper(int num) {
      helper = new Helper(num);
    }
  }
Correct the code as shown in theCompliant Solution below:
  final class Foo {
    private Helper helper;
 
    public synchronized Helper getHelper() {
      return helper;
    }
 
    public synchronized void setHelper(int num) {
      helper = new Helper(num);
    }
  }
