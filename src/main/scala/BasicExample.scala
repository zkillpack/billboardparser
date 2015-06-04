///**
// * Created by Dakota on 6/1/2015.
// */
//import scala.util.parsing.combinator._
//
//class RPC extends JavaTokenParsers {
//  def expr: Parser[Float] = rep(term ~ operator) ^^ {
//    // term~operator lists
//    case terms =>
//      //put operand on stack. for each op, pop pair and replace
//      var stack = List.empty[Float]
//      // Remember last op and default to +
//      var lastOp: (Float, Float) => Float = (x, y) => x+y
//      terms.foreach({
//            // num list and operator
//        // update our op
//          case nums ~ op => lastOp = op; stack = reduce(stack ++ nums, op)
//        })
//      stack.reduceRight((x, y) => lastOp(y, x))
//  }
//
//
//  def term: Parser[List[Float]] = rep(num)
//  def num: Parser[Float] = floatingPointNumber ^^ (_.toFloat)
//  def operator: Parser[(Float, Float) => Float] = ("*" | "/" | "+" | "-") ^^ {
//    case "+" => _ + _
//    case "-" => _ - _
//    case "/" => (x, y) => if (y > 0) x / y else 0.toFloat
//    case "*" => _ * _
//  }
//  def reduce(nums: List[Float], op: (Float, Float) => Float): List[Float] = {
//    // pops last pair off stack, does op, pushes RESULT
//    nums.reverse match {
//      case x :: y :: xs => xs ++ List(op(y, x))
//      case List(x) => List(x)
//      case _ => List.empty[Float]
//    }
//  }
//}
//
//object BasicExample extends RPC {
//  def main(args: Array[String]) {
//    val result = calculate(readLine("Input: "))
//    println(s"Parsed $result")
//  }
//
//  def calculate(expression: String) = parseAll(expr, expression)
//
//}
