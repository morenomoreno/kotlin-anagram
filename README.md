# beyonnex-anagram

This repository contains an interactive Kotlin program that provides two main features:
1. Checks if two texts are anagrams of each other.
2. Out of all inputs to feature #1, provides all the anagrams for a given string.

## Features

### Feature #1: Anagram Check
This feature checks if two given texts are anagrams of each other. According to the definition described in the [Wikipedia page for anagram](https://en.wikipedia.org/wiki/Anagram).

### Feature #2: Anagram Finder
Given the hypothetical invocations of the feature #1 function:
- `f1(A, B)`, `f1(A, C)`, `f1(A, D)`

If A, B, and D are anagrams:
- `f2(A)` should return `[B, D]`
- `f2(B)` should return `[A, D]`
- `f2(C)` should return `[]`

## Setup

### Prerequisites

- [Kotlin](https://kotlinlang.org/)
- [Gradle](https://gradle.org/)
- Java Development Kit (JDK) 8 or higher

### Installation

1. Clone the repository:

   ```sh
   git clone https://github.com/morenomoreno/beyonnex-anagram
   cd beyonnex-anagram
   ```

2. Build the project:

   ```sh
   ./gradlew build
   ```

## Usage

1. Run the application:

   ```sh
   ./gradlew run
   ```

2. Follow the on-screen prompts to:
    - Compare two strings to see if they are anagrams.
    - Check if a given string is an anagram of any previously stored strings.
    - Exit from the program.

## Running Tests

To run the tests, use the following command:

```sh
./gradlew test
```

## Architecture

The project is structured according to Hexagonal Architecture principles, with a clear separation between the domain logic, application services, inbound infrastructure and outbound infrastructure.

1. **Domain**: Contains the core business logic.
2. **Application**: Contains services that use the domain logic and interact with repositories.
3. **Infrastructure**: Contains repository implementations (e.g., in-memory, MongoDB) for the outbound and the CLI implementation for the inbound.

This approach allows to change quickly the repository from a in-memory implementation to a database like MongoDB. Also allows to change the CLI implementation to a REST API or another input implementation. 

## Future Enhancements

- Add more repository implementations (e.g., MongoDB).
- Improve the user interface or change to REST-API