# codextest

This repository contains a minimal Java web server example that listens on port **9099** and serves a small web page. The page lets you select a programming language and shows the typical file extension and a sample Fortify SCA command for that language.

## Running the server

1. Compile the Java source:

   ```bash
   javac src/Main.java
   ```

2. Run the server:

   ```bash
   java -cp src Main
   ```

   The server will start and print `Server running on http://localhost:9099`.

3. Open your browser and navigate to `http://localhost:9099`. You will see a drop-down menu for choosing a language. Selecting one displays the file extension and an example Fortify command.

## Scanning with Fortify SCA

If you have Fortify SCA installed, you can analyze this project as follows:

```bash
sourceanalyzer -b codextest -clean
sourceanalyzer -b codextest javac src/Main.java
sourceanalyzer -b codextest -scan -f codextest.fpr
```

This will produce a Fortify Project Results (FPR) file named `codextest.fpr` that you can open with Fortify Audit Workbench.
