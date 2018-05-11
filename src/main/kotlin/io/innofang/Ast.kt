package io.innofang

/**
 * Created by Inno Fang on 2018/5/11.
 */


open class Ast

class BinOp(val left: Ast, val op: Token, val right: Ast) : Ast() {
    val token = op
}

class Num(val token: Token) : Ast() {
    val value = token.value
}

class UnaryOp(val op: Token, val expr: Ast): Ast(){
    val token = op
}