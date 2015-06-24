/**
 * Created by Dakota on 6/1/2015.
 */
package billboardparser

import fastparse.core.Result
import fastparse.core.Result.{Failure, Success}

import scala.io.Source
import scala.util.Try

object Parser extends BillboardParser {
  val basedir = "C:/chords/McGill-Billboard/"

  def getPath(songId: String): String = {
    basedir + songId + "/salami_chords.txt"
  }

  def parseTranscription(songId: String): Try[Result[_]] =
    Try(transcription.parse(Source.fromFile(getPath(songId)).mkString))

  def printTranscription(trans: Result[_], songId: String) = {
    trans match {
      case s: Success[_] => println(s)
      case f: Failure => println(s"Parse error on $songId: $f")
    }
  }

  def main(args: Array[String]) {
    for (songId <- 353 to 353 map { id => f"$id%04d" }) {
      parseTranscription(songId) match {
        case scala.util.Success(t) => printTranscription(t, songId)
        case scala.util.Failure(e) => e match {
          case e: java.io.FileNotFoundException => ()
          case e: Exception => println(s"Error processing $songId: $e")
        }
      }
    }
  }
}



