#
# A simple makefile for compiling three java classes
#

# define a makefile variable for the java compiler
#
JC = javac

# define a makefile variable for compilation flags
# the -g flag compiles with debugging information
#
JB = -g

# typing 'make' will invoke the first target entry in the makefile
# (the default one in this case)
#
default: Build Run

# this target entry builds the Average class
# the Average.class file is dependent on the Average.java file
# and the rule associated with this entry gives the command to create it
#
Build:
	$(JC) -cp jxl.jar  vagtplan.java user/user.java
Run:
	java -cp jxl.jar:. vagtplan

#Volume.class: Volume.java
#        $(JCC) $(JFLAGS) Volume.java

# To start over from scratch, type 'make clean'.
# Removes all .class files, so that the next make rebuilds them
#
#clean:
#	cd ..
