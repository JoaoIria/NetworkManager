# Network Manager
#### @IST - PO Project 2022

> The objective of the project is to develop an application that will work as the manager of a network of communication terminals, called "prr". Generically, the program allows the management and consultation of clients, terminals and communications. You can read all about the project [here](NetworkManager/document.pdf).

Project made with **[João Iria](https://github.com/JoaoIria)**.



###### About the program:

How to compile: 
```
javac -cp po-uilib.jar:. `find prr -name "*.java"`
```
or
```
find prr -name "*.java" -print | xargs javac -cp po-uilib.jar:.
```

How to run: 
```
java -cp po-uilib.jar:. prr.app.App
```

How to run with import: 
```
java -Dimport=file.im -cp po-uilib.jar:. prr.app.App
```

How to run the tests: 
```
1) chmod +x runtests.sh
2) ./runtests.sh
```
