Rule 15. Platform Security (SEC)
SEC01-J. Do not allow tainted variables in privileged blocks Given the non-compliant code below:
  private void privilegedMethod(final String filename)
                              throws FileNotFoundException {
  try {
    FileInputStream fis =
        (FileInputStream) AccessController.doPrivileged(
          new PrivilegedExceptionAction() {
        public FileInputStream run() throws FileNotFoundException {
          return new FileInputStream(filename);
        }
      }
    );
    // Do something with the file and then close it
  } catch (PrivilegedActionException e) {
    // Forward to handler
  }
}
Correct the code as shown in theCompliant Solution below:
  private void privilegedMethod(final String filename)
                              throws FileNotFoundException {
  final String cleanFilename;
  try {
    cleanFilename = cleanAFilenameAndPath(filename);
  } catch (/* exception as per spec of cleanAFileNameAndPath */) {
    // Log or forward to handler as appropriate based on specification
    // of cleanAFilenameAndPath
  }
  try {
    FileInputStream fis =
        (FileInputStream) AccessController.doPrivileged(
          new PrivilegedExceptionAction() {
        public FileInputStream run() throws FileNotFoundException {
          return new FileInputStream(cleanFilename);
        }
      }
    );
    // Do something with the file and then close it
  } catch (PrivilegedActionException e) {
    // Forward to handler
  }
}
