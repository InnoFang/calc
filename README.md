# calc

A simple command-line calculator written in Kotlin

**PS** this is a practice project of an interpreter, to implement a command-line calculator, I design a series of grammar to do to do so instead of using _reverse Polish notation (RPN)_

# Grammar

```
expr   : factor (( PLUS | MINUS) factor) *
term   : factor (( MUL  | DIV  ) factor) *
factor : INTEGER | LPAREN expr RPAREN
```

# Usage

open your terminal, and type

```
gradle build
```

Then, there is a file named `calc-1.0.0.jar` in the `build/libs`

```
cd build/libs
java -jar calc-1.0.0.jar
```

# Example

you can use like this

```
calc> 12
12
calc> 34 * 56
1904
calc> 1234 + 87123 - 19283
69074
calc> 22 * 12 - 172 + 56
148
calc> 345 / 5 * ( 234 - 92 ) + 66
9864
calc>
```

# [License]()

           Copyright 2018 InnoFang

           Licensed under the Apache License, Version 2.0 (the "License");
           you may not use this file except in compliance with the License.
           You may obtain a copy of the License at

               http://www.apache.org/licenses/LICENSE-2.0

           Unless required by applicable law or agreed to in writing, software
           distributed under the License is distributed on an "AS IS" BASIS,
           WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
           See the License for the specific language governing permissions and
           limitations under the License.