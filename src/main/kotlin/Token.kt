/**
 * Created by Inno Fang on 2018/5/10.
 */


data class Token(private val type: String, private val value: Any) {
    companion object {
        val INTEGER = "INTEGER"
        val PLUS = "PLUS"
        val MINUS = "MINUS"
        val MUL = "MUL"
        val DIV = "DIV"
        val LPAREN = "("
        val RPAERN = ")"
        val EOF = "EOF"
    }

    override fun toString(): String {
        return "Token($type, $value)"
    }
}