package io.innofang

/**
 * Created by Inno Fang on 2018/5/10.
 */

import io.innofang.TokenType.*

abstract class NodeVisitor {
    protected fun error(): Nothing = throw Exception("Invalid type")

    fun visit(ast: Ast): Int {
        if (ast is BinOp) return visitBinOp(ast)
        if (ast is Num) return visitNum(ast)
        if (ast is UnaryOp) return visitUnaryOp(ast)
        error()
    }

    abstract fun visitBinOp(binOp: BinOp): Int
    abstract fun visitNum(num: Num): Int
    abstract fun visitUnaryOp(unaryOp: UnaryOp): Int
}

class Interpreter(private val parser: Parser) : NodeVisitor() {

    override fun visitBinOp(binOp: BinOp): Int {
        return when (binOp.op.type) {
            PLUS -> visit(binOp.left) + visit(binOp.right)
            MINUS -> visit(binOp.left) - visit(binOp.right)
            MUL -> visit(binOp.left) * visit(binOp.right)
            DIV -> visit(binOp.left) / visit(binOp.right)
            else -> error()
        }
    }

    override fun visitNum(num: Num): Int {
        return num.value as Int
    }

    override fun visitUnaryOp(unaryOp: UnaryOp): Int {
        val op = unaryOp.op.type
        if (op == PLUS) {
            return +visit(unaryOp.expr)
        } else if (op == MINUS) {
            return -visit(unaryOp.expr)
        }
        error()
    }

    fun interpret(): Int {
        val tree = parser.parse()
        return visit(tree)
    }
}