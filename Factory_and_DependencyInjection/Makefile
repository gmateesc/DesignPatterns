builddir = ../classes
project = optravis/messaging

fdemo    := FactoryPatternDemo.java
fmain    := ${fdemo:.java=}

all: depinject factory

depinject:
	@echo " "
	@echo "Making dependency injection pattern code"
	mkdir -p classes
	cd src && javac  -d ${builddir} ${project}/service/*.java
	cd src && javac  -d ${builddir} ${project}/client/*.java
	cd src && javac  -d ${builddir} ${project}/depinject/injector/*.java
	cd src && javac  -d ${builddir} ${project}/depinject/demo/*.java
	@echo " "
	@echo "Running MessageServiceDemo"
	java -cp .:classes ${project}/depinject/demo/MessageServiceDemo



factory:
	@echo " "
	@echo "Making factory pattern code"
	mkdir -p classes
	cd src && javac  -d ${builddir} ${project}/service/*.java
	cd src && javac  -d ${builddir} ${project}/factorypattern/factory/*.java
	cd src && javac  -d ${builddir} ${project}/factorypattern/demo/${fdemo}
	@echo " "
	@echo "Running ${fmain}"
	java -cp .:classes ${project}/factorypattern/demo/${fmain}




clean:
	rm -rf classes
