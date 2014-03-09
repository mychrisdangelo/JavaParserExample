.PHONY all:
	javac -cp javaparser-1.0.8.jar ParserExample.java

clean: 
	rm *.class *~
