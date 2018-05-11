/**
 * Created by Inno Fang on 2018/5/10.
 */

class Token(private val type: TokenType, private val value: Any?) {

    override fun toString(): String {
        return "Token($type, $value)"
    }
}