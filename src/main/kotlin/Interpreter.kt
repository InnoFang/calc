/**
 * Created by Inno Fang on 2018/5/10.
 */

import TokenType.*

interface NodeVisitor {
    fun visit(binOp: BinOp): Int
    fun visit(num: Num): Int
}

class Interpreter(val parser: Parser): NodeVisitor {

    private fun error(): Nothing = throw Exception("Invalid type")

    override fun visit(node: BinOp): Int {
        return when (node.op.type) {
            PLUS -> visit(node.left as Num) + visit(node.right as Num)
            MINUS -> visit(node.left as Num) - visit(node.right as Num)
            MUL -> visit(node.left as Num) * visit(node.right as Num)
            DIV -> visit(node.left as Num) / visit(node.right as Num)
            else -> error()
        }
    }

    override fun visit(node: Num): Int {
        return node.value as Int
    }

    fun interpret(): Int {
        val tree = parser.parse()
        return visit(tree)
    }
}