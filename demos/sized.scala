// by Miles Sabin (@milessabin)

import shapeless._
import syntax.sized._

object SizedExample extends App {
  
  def row(cols : Seq[String]) = cols.mkString("\"", "\", \"", "\"")
  
  def csv[N <: Nat](hdrs : Sized[Seq[String], N], rows : List[Sized[Seq[String], N]]) =
    row(hdrs) :: rows.map(row(_))
  
  val hdrs = Sized("Title", "Author")                     // Sized[IndexedSeq[String], _2]
  val rows = List(                                        // List[Sized[IndexedSeq[String], _2]]
    Sized("Types and Programming Languages", "Benjamin Pierce"),
    Sized("The Implementation of Functional Programming Languages", "Simon Peyton-Jones")
  )

  // hdrs and rows statically known to have the same number of columns
  val formatted = csv(hdrs, rows)
  formatted foreach println                               // Compiles
  
  println
  
  // extendedHdrs has the wrong number of columns for rows
  val extendedHdrs = Sized("Title", "Author", "ISBN")     // Sized[IndexedSeq[Int], _3]
  //val badFormatted = csv(extendedHdrs, rows)            // Does not compile

  // Extend the rows to match ...
  val extendedRows = rows map (_ :+ "-")                  // List[Sized[IndexedSeq[String], _3]]
  
  val extendedFormatted = csv(extendedHdrs, extendedRows) // Compiles
  extendedFormatted foreach println

}
