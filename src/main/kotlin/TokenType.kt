/**
 * Created by Inno Fang on 2018/5/10.
 */

enum class TokenType(val type: String) {
    INTEGER("INTEGER"),
    PLUS("PLUS"),
    MINUS("MINUS"),
    MUL("MUL"),
    DIV("DIV"),
    LPAREN("("),
    RPAREN(")"),
    EOF("EOF"),
}
