# The buffer works like a FIFO queue in java using separate threads

The program reads characters from a given input file into an in-memory buffer one at a time and writes characters from the same buffer to a given output file.

## System requirements

1. Make sure you have JDK 8 or newer JDK installed.

2. Maven is also required, which can also be installed via [SDKMAN!](https://sdkman.io)!


##Build and Deploy the Application

1. Open a command line and navigate to the directory of this project

2. Type this command to build and deploy the archive:

	mvn clean package install

3. You should see the following console output when you run the tests:

	Results :
	Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
    
4. Run an Executable JAR with Arguments

Here's an example with two arguments, must be same path command to the JAR file:

	java -jar target/ThreadBuffer-0.1.jar input.txt output.txt