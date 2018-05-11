/**
 * Created by Inno Fang on 2018/5/10.
 */


import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    while (true) {
        try {
            print("calc> ")
            val text = BufferedReader(InputStreamReader(System.`in`)).readLine() ?: continue
            val lexer = Lexer(text)
            val parser = Parser(lexer)
            val interpreter = Interpreter(parser)
            interpreter.interpret().let(::println)
        } catch (e: Exception) {
            e.printStackTrace()
            break
        }
    }
}