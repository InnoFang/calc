/**
 * Created by Inno Fang on 2018/5/10.
 */

class Token(val type: TokenType, val value: Any?) {

    override fun toString(): String {
        return "Token($type, $value)"
    }
}