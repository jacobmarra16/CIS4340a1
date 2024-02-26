Rule 13. Input Output (FIO)
FIO01-J. Create files with appropriate access permissions Given the non-compliant code below:
  Writer out = new FileWriter("file");
Correct the code as shown in theCompliant Solution below:
  Path file = new File("file").toPath();
 
  // Throw exception rather than overwrite existing file
  Set<OpenOption> options = new HashSet<OpenOption>();
  options.add(StandardOpenOption.CREATE_NEW);
  options.add(StandardOpenOption.APPEND);
 
  // File permissions should be such that only user may read/write file
  Set<PosixFilePermission> perms =
     PosixFilePermissions.fromString("rw-------");
  FileAttribute<Set<PosixFilePermission>> attr =
    PosixFilePermissions.asFileAttribute(perms);
 
  try (SeekableByteChannel sbc =
         Files.newByteChannel(file, options, attr)) {
    // Write data
  };
