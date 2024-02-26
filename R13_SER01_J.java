Rule 14. Serialization (SER)
SER01-J. Do not deviate from the proper signatures of serialization methods Given the non-compliant code below:
  public class Ser implements Serializable {
  private final long serialVersionUID = 123456789;
  private Ser() {
    // Initialize
  }
  public static void writeObject(final ObjectOutputStream stream)
    throws IOException {
    stream.defaultWriteObject();
  }
  public static void readObject(final ObjectInputStream stream)
      throws IOException, ClassNotFoundException {
    stream.defaultReadObject();
  }
}
Correct the code as shown in theCompliant Solution below:
  private void writeObject(final ObjectOutputStream stream)
    throws IOException {
  stream.defaultWriteObject();
}
 
private void readObject(final ObjectInputStream stream)
    throws IOException, ClassNotFoundException {
  stream.defaultReadObject();
}
