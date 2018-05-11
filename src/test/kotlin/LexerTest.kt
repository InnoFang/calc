import org.junit.Test

/**
 * Created by Inno Fang on 2018/5/11.
 */

class LexerTest {
    @Test
    fun test() {
        val lexer = Lexer("2 *  (5 + 1) / 3 - 1 ")
//        val lexer = Lexer("1+2")
        lexer.getNextToken().let(::println)
        lexer.getNextToken().let(::println)
        lexer.getNextToken().let(::println)
        lexer.getNextToken().let(::println)
        lexer.getNextToken().let(::println)
        lexer.getNextToken().let(::println)
        lexer.getNextToken().let(::println)
        lexer.getNextToken().let(::println)
        lexer.getNextToken().let(::println)
        lexer.getNextToken().let(::println)
        lexer.getNextToken().let(::println)
        lexer.getNextToken().let(::println)
    }
}
