### ubuntu Build Instructions

#### step 1: install the required software

`sudo apt-get install maven git`

#### step 2: clone the source code

`git clone https://github.com/crazybits/p2p.git`

#### step 3: run the starter

`cd p2p;mvn compile`

`mvn exec:java -Dexec.mainClass="Starter"`
