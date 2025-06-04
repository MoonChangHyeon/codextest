# codextest

This repository contains a minimal Java web server example that listens on port **9099** and serves a small web page. The page lets you select a programming language and shows the typical file extension and a sample Fortify SCA command for that language.

## Running the server

You need [Maven](https://maven.apache.org/) installed.

1. Build and run directly with Maven:

   ```bash
   mvn compile exec:java
   ```

   The server will start and print `Server running on http://localhost:9099`.

2. Alternatively, package the project and run the class manually:

   ```bash
   mvn package
   java -cp target/classes com.example.Main
   ```

3. Open your browser and navigate to `http://localhost:9099`. You will see a centered drop-down menu and a **Run** button. Choose a language and press **Run** to display the file extension and a sample Fortify command below the selector.

## Scanning with Fortify SCA

If you have Fortify SCA installed, you can analyze this project as follows:

```bash
sourceanalyzer -b codextest -clean
sourceanalyzer -b codextest mvn -q compile
sourceanalyzer -b codextest -scan -f codextest.fpr
```

This will produce a Fortify Project Results (FPR) file named `codextest.fpr` that you can open with Fortify Audit Workbench.
