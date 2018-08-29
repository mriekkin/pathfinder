# Testing document

## Unit Testing

Approximately 190 unit tests, written with the JUnit unit testing framework. Line coverage of 98 % and branch coverage of 95 %.

![JaCoCo: summary of code coverage](img/test_coverage.png)

Unit tests can be run from the command-line with the command

```
gradle test jacocoTestReport
```

This command creates two reports: a test report and a JaCoCo code coverage report. They are located under the directory ```build``` and can be opened as follows:

```
open build/reports/tests/test/index.html
open build/reports/jacoco/test/html/index.html
```

The rest of this document describes package-by-package what kind of unit testing is done. All packages except the GUI have unit tests. The GUI packages do not have unit tests.

### Package pathfinder.benchmark
### Package pathfinder.datastructures
### Package pathfinder.io
### Package pathfinder.logic
### Package pathfinder.logic.neighbours
### Package pathfinder.logic.pathfinders

## Manual testing
