/**
 * Created by Inno Fang on 2018/5/10.
 */

import TokenType.*

public class Lexer(private val text: String) {
    private var pos: Int = 0
    private var currentChar: Char? = text[pos]

    private fun error(): Nothing = throw Exception("Invalid character")

    private fun advance() {
        pos += 1
        currentChar = if (pos > text.lastIndex) null else text[pos]
    }

    private fun skipWhitespace() {
        while (currentChar != null && currentChar!!.isWhitespace()) {
            advance()
        }
    }

    private fun integer(): Int {
        var result = ""
        while (currentChar != null && currentChar!!.isDigit()) {
            result += currentChar
            advance()
        }
        return result.toInt()
    }

    fun getNextToken(): Token {
        while (currentChar != null) {
            if (currentChar!!.isWhitespace()) {
                skipWhitespace()
                continue
            }
            if (currentChar!!.isDigit()) {
                return Token(INTEGER, integer())
            }
            currentChar?.let {
                when (it) {
                    '+' -> {
                        advance()
                        return Token(PLUS, '+')
                    }
                    '-' -> {
                        advance()
                        return Token(MINUS, '-')
                    }
                    '*' -> {
                        advance()
                        return Token(MUL, '*')
                    }
                    '/' -> {
                        advance()
                        return Token(DIV, '/')
                    }
                    '(' -> {
                        advance()
                        return Token(LPAREN, '(')
                    }
                    ')' -> {
                        advance()
                        return Token(RPAREN, ')')
                    }
                    else -> error()
                }
            }
        }
        return Token(EOF, null)
    }
}