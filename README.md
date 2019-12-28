# Binary Object loggable.Logger Implementation
[![Build Status](https://travis-ci.com/georgeerol/BinaryObjectLogger.svg?branch=master)](https://travis-ci.com/georgeerol/BinaryObjectLogger)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=georgeerol_BinaryObjectLogger&metric=alert_status)](https://sonarcloud.io/dashboard?id=georgeerol_BinaryObjectLogger)

## Problem
Using given interface and abstract class definitions, implement a Binary Object loggable.Logger.
 
The basic idea is to have a binary object logger class instantiated with a file path where the log will be written. 
The write method accepts objects implementing a "binary loggable" interface that defines methods for writing and reading those objects to/from an array of bytes. 
Binary object logger will write such objects into a given binary file, and it will support reading objects back from the binary file in an iterative fashion.

You should come up with a binary file schema that supports this interface.
Your implementation should be simple, robust, and with minimal overhead, so that it could be used in a performance-critical environment. This means minimal CPU and memory resources used when logger writes into the log or reads from the log. You do not have to worry about multiple threads trying to write into the same binary logger.




###  binarylogger.BinaryLogger interface looks as follows:

```java
/**
 * binarylogger.BinaryLogger logs serialized instances of {@link BinaryLoggable} into
file.
 * It does so in such a way that it is possible to stream these
instances back
 * in an iterative fashion via the {@link #read(File, Class)} method.
 */
public abstract class binarylogger.BinaryLogger<T extends BinaryLoggable> implements
AutoCloseable {
   protected File outputFile;
   public binarylogger.BinaryLogger(File file) {
       this.outputFile = file;
}
  /**
   * Writes the serialized instance.
   *
   * @param loggable an instance of {@code loggable.BinaryLoggable} that needs to
be logged
   * @throws IOException if any IO operation fails
   */
   abstract void write(T loggable) throws IOException;
   /**
   * Read and iterate through instances persisted in the given file.
   *
   * @param clazz a class of the type T, clazz should have a public
no-arg constructor
   * @throws IOException if any IO operation fails
   */
   abstract Iterator<T> read(Class<T> clazz) throws IOException;
}

```
### loggable.BinaryLoggable interface is:
```java
/**
 * loggable.BinaryLoggable represents an entity that can be logged by {@code
binarylogger.BinaryLogger}.
 */
public interface loggable.BinaryLoggable {
  /**
   * Serialize the fields of this object into a byte array.
   */
   byte[] toBytes() throws IOException;
  /**
   * Deserialize the fields of this object from given byte array.
   */
   void fromBytes(byte[] rawBytes) throws IOException;
}
```
# Class Diagram
* `BinaryLogger` - This class logs serialized instances of `{@code BinaryLoggable}` into file.
* `BinaryLoggable` - This class represents an entity that can be logged by `{@code BinaryLogger}`.
* `BinaryFileReader` - This class reads `{@code BinaryLoggable}s` from a provided file.
* `BinaryLogFile` -  This class is the implementation of `{@code BinaryLogger}`. It writes and reads `{@code BinaryLoggable}s`
 to the provided file. 
* `ClassValidation` - This class checks if a class name exit and is an implementation of `{@code BinaryLoggable}`.
* `FileValidation` - This class checks the provided file exist or can be created.
*  `Loggable` - This class represents an entity that can be logged by `{@code BinaryLogger}`.
*  `Systemlog` - This class extends Loggable and it represents an entity that can be logged by {@code BinaryLogger}.

![Class Diagram](./misc/ClassDiagram.png)


# Code Analysis with Sonarcloud
![Class Diagram](./misc/codeAnalysis.png)


# Run the project from Intellij IDE
To Run the maven project from Intellij, run the Maven Project Lifecycle `clean` then `package`
Maven Projects is located under `Views` -> `Tools Windows`

#### Maven Projects Location
![Maven Projects Location](./misc/mvnProjectsLocation.png)


From the Lifecycle run `clean` then `pakckage`. After succeeding this step, run the main class`Main` 
with an argument by editing the configuration and adding the argument file `LogTestFile`

##### Lifecycle Clean and Package
![Lifecycle](./misc/lifecycleCleanAndPackage.png)

##### Configuration to run the APP from Intellij IDE
![Edit configuration](./misc/editConfigurations.png)
![Run App](./misc/runAppFromIDE.png)


**Note**: Feel free to use your own file!


## To run the project from command line
To build and compile the project via the command line do a  maven `clean` and then `package`
If maven is not  install on your computer use the maven wrapper with `./mvnw` instead of `mvn`
### Maven Clean
```bash
$ mvn clean
```
#### Maven Response:
![mvn clean](./misc/mvnClean.png)

### Maven Package
```bash
$ mvn package
```
#### Maven Response:
![mvn package](./misc/mvnPackage.png)

## The Jar File
After running `mvn package` a target folder will be created and it will contain the jar file: `Binary-object-logger-1.0-SNAPSHOT.jar`.

#### Target folder
![target folder](./misc/targetFolder.png)

From the command line or from the IDE move the `LogTestFile` to the **target** folder
![Log Test File](./misc/LogTestFileLocation.png)


After moving the file, `cd` to the target folder and run the program via the command line:
```bash
$ java -jar Binary-object-logger-1.0-SNAPSHOT.jar LogTestFile 
```

**Note**: Feel free to use your own file!







