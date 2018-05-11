package io.innofang

/**
 * Created by Inno Fang on 2018/5/10.
 */

import io.innofang.TokenType.*

class Parser(private val lexer: Lexer) {
    private var currentToken = lexer.getNextToken()

    private fun error(): Nothing = throw Exception("Invalid syntax")

    private fun eat(tokenType: TokenType) {
        if (tokenType == currentToken.type) {
            currentToken = lexer.getNextToken()
        } else {
            error()
        }
    }


    /**
     * factor: INTEGER | LPAREN expr RPAREN
     */
    private fun factor(): Ast {
        val token = currentToken
        if (token.type == INTEGER) {
            eat(INTEGER)
            return Num(token)
        } else if (token.type == LPAREN) {
            eat(LPAREN)
            val node = expr()
            eat(RPAREN)
            return node
        }
        error()
    }

    /**
     * term : factor (( MUL  | DIV  ) factor) *
     */
    private fun term(): Ast {
        var node = factor()

        while (currentToken.type in setOf(MUL, DIV)) {
            val token = currentToken

            if (token.type == MUL) {
                eat(MUL)
            } else {
                eat(DIV)
            }
            node = BinOp(left = node, op = token, right = factor())
        }
        return node
    }

    /**
     * expr : factor (( PLUS | MINUS) factor) *
     */
    private fun expr(): Ast {
        var node = term()
        while (currentToken.type in setOf(PLUS, MINUS)) {
            val token = currentToken
            if (token.type == PLUS) {
                eat(PLUS)
            } else {
                eat(MINUS)
            }
            node = BinOp(left = node, op = token, right = term())
        }
        return node
    }

    fun parse() = expr()

}